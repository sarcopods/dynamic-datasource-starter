package io.github.bozrahvice.shardingjdbc.provider;


import io.github.bozrahvice.shardingjdbc.commons.ErrorCreateDataSourceException;
import io.github.bozrahvice.shardingjdbc.initdatasource.DataSourceCreator;
import io.github.bozrahvice.shardingjdbc.properties.CommonConnectionPoolProperties;
import io.github.bozrahvice.shardingjdbc.properties.ConnectionPoolProperty;
import io.github.bozrahvice.shardingjdbc.properties.ConnectionPoolUtils;
import io.github.bozrahvice.shardingjdbc.properties.DataSourceProperty;
import io.github.bozrahvice.shardingjdbc.properties.DynamicDataSourceProperties;
import io.github.bozrahvice.shardingjdbc.properties.shardingsphere.ShardingJdbcDataSourceProperties;
import io.github.bozrahvice.shardingjdbc.properties.shardingsphere.masterslave.MasterSlaveRuleConfigurationProperties;
import io.github.bozrahvice.shardingjdbc.properties.shardingsphere.sharding.ShardingRuleConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.yaml.swapper.MasterSlaveRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.core.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Slf4j
public class AbstractDynamicDataSourceProvider {

    @Resource
    private DataSourceCreator dataSourceCreator;

    @Resource
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    @Resource
    private ShardingJdbcDataSourceProperties shardingJdbcDataSourceProperties;

    @Resource
    private CommonConnectionPoolProperties commonConnectionPoolProperties;

    private final ShardingRuleConfigurationYamlSwapper shardingSwapper = new ShardingRuleConfigurationYamlSwapper();

    private final MasterSlaveRuleConfigurationYamlSwapper masterSlaveSwapper = new MasterSlaveRuleConfigurationYamlSwapper();

    private final static Pattern REG = Pattern.compile("[\\d]");

    protected Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        Map<String, DataSourceProperty> simpleDatasourceMap = dynamicDataSourceProperties.getDatasource();
        Map<String, ConnectionPoolProperty> simpleConnectionPoolMap = dynamicDataSourceProperties.getConnectionPool();
        if (!simpleDatasourceMap.isEmpty()) {
            log.info("simple dataSource properties is not empty,load dataSource");
            simpleDatasourceMap.forEach((ds, dataSourceProperty) -> {
                dataSourceProperty.setName(ds);
                ConnectionPoolProperty connectionPoolProperty = ConnectionPoolUtils.rebuildConnectionPool(simpleConnectionPoolMap.get(ds), commonConnectionPoolProperties.getConnectionPool());
                dataSourceMap.put(ds, dataSourceCreator.createDataSource(dataSourceProperty, connectionPoolProperty));
            });
        } else {
            log.warn("simple dataSource properties is empty");
        }
        Map<String, DataSourceProperty> shardingDatasourceMap = shardingJdbcDataSourceProperties.getDatasource();
        Map<String, ConnectionPoolProperty> shardingConnectionPoolMap = shardingJdbcDataSourceProperties.getConnectionPool();
        Map<String, ShardingRuleConfigurationProperties> shardingMap = shardingJdbcDataSourceProperties.getSharding();
        Map<String, MasterSlaveRuleConfigurationProperties> masterSlaveRulesMap = shardingJdbcDataSourceProperties.getMasterSlaveRules();
        Properties props = shardingJdbcDataSourceProperties.getProps();
        List<String> groupIdList = shardingJdbcDataSourceProperties.getGroupIds();
        if (groupIdList.isEmpty()) {
            log.warn("sharding dataSource groupsIds is empty,can not load sharding dataSource");
            return dataSourceMap;
        }
        if (!shardingDatasourceMap.isEmpty()) {
            log.info("sharding dataSource properties is not empty,load dataSource");
            Map<String, Map<String, DataSourceProperty>> shardingGroupDatasourceMap = new LinkedHashMap<>();
            groupIdList.forEach(groupId -> shardingDatasourceMap.forEach((key, dataSourceProperty) -> {
                if (key.startsWith(groupId)) {
                    Map<String, DataSourceProperty> cacheMap = null == shardingGroupDatasourceMap.get(groupId) ? new HashMap<>() : shardingGroupDatasourceMap.get(groupId);
                    cacheMap.put(key, dataSourceProperty);
                    shardingGroupDatasourceMap.put(groupId, cacheMap);
                }
            }));
            shardingGroupDatasourceMap.forEach((groupId, stringDataSourcePropertyMap) -> {
                Map<String, DataSource> logicDataSourceMap = new HashMap<>();
                stringDataSourcePropertyMap.forEach((logicId, dataSourceProperty) -> {
                    dataSourceProperty.setName(logicId);
                    ConnectionPoolProperty connectionPoolProperty = ConnectionPoolUtils.rebuildConnectionPool(shardingConnectionPoolMap.get(logicId), commonConnectionPoolProperties.getConnectionPool());
                    logicDataSourceMap.put(logicId, dataSourceCreator.createDataSource(dataSourceProperty, connectionPoolProperty));
                });
                if (!shardingMap.isEmpty() && null != shardingMap.get(groupId)) {
                    ShardingRuleConfigurationProperties shardingRuleConfigurationProperties = shardingMap.get(groupId);
                    DataSource dataSource;
                    try {
                        dataSource = ShardingDataSourceFactory.createDataSource(logicDataSourceMap, shardingSwapper.swap(shardingRuleConfigurationProperties), props);
                    } catch (Exception e) {
                        throw new ErrorCreateDataSourceException("dataSource create error,", e);
                    }
                    dataSourceMap.put(groupId, dataSource);
                } else if (!masterSlaveRulesMap.isEmpty() && null != masterSlaveRulesMap.get(groupId)) {
                    MasterSlaveRuleConfigurationProperties masterSlaveRuleConfigurationProperties = masterSlaveRulesMap.get(groupId);
                    DataSource dataSource;
                    try {
                        dataSource = MasterSlaveDataSourceFactory.createDataSource(logicDataSourceMap, masterSlaveSwapper.swap(masterSlaveRuleConfigurationProperties), props);
                    } catch (Exception e) {
                        throw new ErrorCreateDataSourceException("dataSource create error,", e);
                    }
                    dataSourceMap.put(groupId, dataSource);
                }
            });
        } else {
            log.warn("sharding dataSource properties is  empty");
        }
        return dataSourceMap;
    }

}
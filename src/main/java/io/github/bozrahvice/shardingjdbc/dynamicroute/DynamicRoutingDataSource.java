package io.github.bozrahvice.shardingjdbc.dynamicroute;

import io.github.bozrahvice.shardingjdbc.commons.DynamicDataSourceContextHolder;
import io.github.bozrahvice.shardingjdbc.commons.ErrorCreateDataSourceException;
import io.github.bozrahvice.shardingjdbc.provider.DynamicDataSourceProvider;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {

    @Setter
    private DynamicDataSourceProvider provider;

    /**
     * 所有数据源
     */
    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();

    /**
     * 数据源中第一个
     */
    private String primary;

    @Override
    protected DataSource determineDataSource() {
        return getDataSource(DynamicDataSourceContextHolder.peek());
    }

    private DataSource determineDefaultDataSource() {
        log.info("dynamic-datasource switch to the default datasource");
        return dataSourceMap.get(primary);
    }

    /**
     * 获取数据源
     */
    public DataSource getDataSource(String ds) {
        if (StringUtils.isEmpty(ds)) {
            return determineDefaultDataSource();
        } else if (dataSourceMap.containsKey(ds)) {
            log.info("dynamic-datasource switch to the datasource named [{}]", ds);
            return dataSourceMap.get(ds);
        } else {
            throw new ErrorCreateDataSourceException("dataSource key:[" + ds + "]is not find");
        }
    }

    /**
     * 获取当前所有的数据源
     *
     * @return 当前所有数据源
     */
    public Map<String, DataSource> getDataSources() {
        return dataSourceMap;
    }

    @Override
    public void destroy() throws Exception {
        log.info("dynamic-datasource start closing ....");
        for (Map.Entry<String, DataSource> item : dataSourceMap.entrySet()) {
            closeDataSource(item.getKey(), item.getValue());
        }
        log.info("dynamic-datasource all closed success,bye");
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, DataSource> dataSources = provider.loadDataSources();
        for (Map.Entry<String, DataSource> dsItem : dataSources.entrySet()) {
            addDataSource(dsItem.getKey(), dsItem.getValue());
        }
        if (!dataSources.isEmpty()) {
            this.primary = dataSources.keySet().stream().findFirst().get();
        }
    }


    private void closeDataSource(String name, DataSource dataSource) throws Exception {
        Class<? extends DataSource> clazz = dataSource.getClass();
        try {
            if (dataSource instanceof ShardingDataSource) {
                ShardingDataSource shardingDataSource = (ShardingDataSource) dataSource;
                shardingDataSource.close();
            } else {
                Method closeMethod = clazz.getDeclaredMethod("close");
                closeMethod.setAccessible(true);
                closeMethod.invoke(dataSource);
            }
        } catch (NoSuchMethodException e) {
            log.warn("dynamic-datasource close the datasource named [{}] failed,", name);
        }
    }

    public synchronized void addDataSource(String ds, DataSource dataSource) {
        if (!dataSourceMap.containsKey(ds)) {
            dataSourceMap.put(ds, dataSource);
            log.info("dynamic-datasource - load a datasource named [{}] success", ds);
        } else {
            log.warn("dynamic-datasource - load a datasource named [{}] failed, because it already exist", ds);
        }
    }
}
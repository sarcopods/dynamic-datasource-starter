package io.github.bozrahvice.shardingjdbc.properties.shardingsphere;

import io.github.bozrahvice.shardingjdbc.properties.ConnectionPoolProperty;
import io.github.bozrahvice.shardingjdbc.properties.DataSourceProperty;
import io.github.bozrahvice.shardingjdbc.properties.shardingsphere.masterslave.MasterSlaveRuleConfigurationProperties;
import io.github.bozrahvice.shardingjdbc.properties.shardingsphere.sharding.ShardingRuleConfigurationProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = ShardingJdbcDataSourceProperties.PREFIX_NAME)
@PropertySource({ShardingJdbcDataSourceProperties.CLASS_PATH})
@Getter
@Setter
public class ShardingJdbcDataSourceProperties {
    public static final String PREFIX_NAME = "jdbc.shardingsphere";

    public static final String CLASS_PATH = "classpath:jdbcdynamic.properties";

    public static final String FILE_NAME = "jdbcdynamic.properties";


    private Properties props = new Properties();

    /**
     * 数据源集合
     */
    private Map<String, DataSourceProperty> datasource = new LinkedHashMap<>();

    /**
     * 连接池配置集合
     */
    private Map<String, ConnectionPoolProperty> connectionPool = new LinkedHashMap<>();

    /**
     * 分片规则 Map
     * key 定义为组id
     */
    private Map<String, ShardingRuleConfigurationProperties> sharding = new LinkedHashMap<>();

    /**
     * 读写分离配置属性
     */
    private Map<String, MasterSlaveRuleConfigurationProperties> masterSlaveRules = new LinkedHashMap<>();

    /**
     * 数据源组列表
     */
    List<String> groupIds = new ArrayList<>();

}
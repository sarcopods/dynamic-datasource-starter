package io.github.bozrahvice.shardingjdbc.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = DynamicDataSourceProperties.PREFIX_NAME)
@PropertySource({DynamicDataSourceProperties.CLASS_PATH})
@Getter
@Setter
public class DynamicDataSourceProperties {

    public static final String PREFIX_NAME = "jdbc.dynamic";

    public static final String CLASS_PATH = "classpath:jdbcdynamic.properties";

    public static final String FILE_NAME = "jdbcdynamic.properties";

    /**
     * 数据源集合
     */
    private Map<String, DataSourceProperty> datasource = new LinkedHashMap<>();

    /**
     * 连接池配置集合
     */
    private Map<String, ConnectionPoolProperty> connectionPool = new LinkedHashMap<>();
}
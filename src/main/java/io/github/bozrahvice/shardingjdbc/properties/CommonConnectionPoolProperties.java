package io.github.bozrahvice.shardingjdbc.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = CommonConnectionPoolProperties.PREFIX_NAME)
@PropertySource({CommonConnectionPoolProperties.CLASS_PATH})
@Getter
@Setter
public class CommonConnectionPoolProperties {

    public static final String PREFIX_NAME = "jdbc.common";

    public static final String CLASS_PATH = "classpath:jdbcdynamic.properties";

    public static final String FILE_NAME = "jdbcdynamic.properties";

    /**
     * 公共的链接池属性，若实际数据源未配置则以这个为准，配置了则以实际数据源下配置的为准
     */
    private ConnectionPoolProperty connectionPool = new ConnectionPoolProperty();
}
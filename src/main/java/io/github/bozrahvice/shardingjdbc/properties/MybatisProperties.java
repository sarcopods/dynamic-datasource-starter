package io.github.bozrahvice.shardingjdbc.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = MybatisProperties.PREFIX_NAME)
@PropertySource({MybatisProperties.CLASS_PATH})
@Getter
@Setter
public class MybatisProperties {

    public static final String PREFIX_NAME = "mybatis";

    public static final String CLASS_PATH = "classpath:jdbcdynamic.properties";

    public static final String FILE_NAME = "jdbcdynamic.properties";

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    private String typeAliasesPackage;

    private String[] mapperLocations = new String[]{"classpath*:/mapper/**/*.xml"};


    public Resource[] resolveMapperLocations() {
        return Stream.of(Optional.ofNullable(this.mapperLocations).orElse(new String[0]))
                .flatMap(location -> Stream.of(getResources(location))).toArray(Resource[]::new);
    }

    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException e) {
            return new Resource[0];
        }
    }

}
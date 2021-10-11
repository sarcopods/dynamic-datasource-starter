package io.github.bozrahvice.shardingjdbc.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author ylpanda
 * @since 1.0.0
 */
public interface DynamicDataSourceProvider {

    /**
     * load all datasource
     *
     * @return java.util.Map所有数据源，key为数据源名称
     * @date 2021/8/20 11:02
     */
    Map<String, DataSource> loadDataSources();
}

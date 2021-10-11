package io.github.bozrahvice.shardingjdbc.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author ylpanda
 * @since 1.0.0
 */
public class DynamicDataSourceServiceImpl extends AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider {

    @Override
    public Map<String, DataSource> loadDataSources() {
        return createDataSourceMap();
    }
}
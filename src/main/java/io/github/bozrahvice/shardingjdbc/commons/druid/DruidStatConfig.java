package io.github.bozrahvice.shardingjdbc.commons.druid;

import lombok.Data;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Data
public class DruidStatConfig {

    private Long slowSqlMillis;

    private Boolean logSlowSql;

    private Boolean mergeSql;
}
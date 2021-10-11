package io.github.bozrahvice.shardingjdbc.commons.druid;

import lombok.Data;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Data
public class DruidSlf4jConfig {

    private Boolean enable = true;

    private Boolean statementExecutableSqlLogEnable = false;
}
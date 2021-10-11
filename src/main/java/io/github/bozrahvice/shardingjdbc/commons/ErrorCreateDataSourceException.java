package io.github.bozrahvice.shardingjdbc.commons;

/**
 * @author ylpanda
 * @since 1.0.0
 */
public class ErrorCreateDataSourceException extends RuntimeException {

    public ErrorCreateDataSourceException(String message) {
        super(message);
    }

    public ErrorCreateDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}

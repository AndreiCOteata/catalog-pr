package org.unibuc.logger;

public interface Logger {
    void info(String message);
    void error(String errorMessage);
    void debug(String debugMessage);
}

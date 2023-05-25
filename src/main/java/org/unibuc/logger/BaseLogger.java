package org.unibuc.logger;

import org.apache.commons.lang3.StringUtils;
import org.unibuc.config.AppConfig;
import org.unibuc.file.FileWriterService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseLogger implements Logger {

    private static BaseLogger instance;

    public static final String LOG_TEMPLATE = "%s   %s --- [%s] %s: %s\n";

    private final FileWriterService fileWriterService = FileWriterService.getInstance();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final AppConfig config = AppConfig.getInstance();

    private BaseLogger() {
    }

    public static synchronized BaseLogger getInstance() {
        if (instance == null) {
            instance = new BaseLogger();
        }
        return instance;
    }

    @Override
    public void info(String message) {
        this.writeAndPrintLog(message, "INFO");

    }

    @Override
    public void error(String errorMessage) {
        this.writeAndPrintLog(errorMessage, "ERROR");
    }

    @Override
    public void debug(String debugMessage) {
        if (config.getProperty("logger.debug").equals("true")) {
            this.writeAndPrintLog(debugMessage, "DEBUG");
        }
    }

    private void writeAndPrintLog(String message, String type) {
        String error = String.format(LOG_TEMPLATE, LocalDateTime.now().format(formatter), type, StringUtils.leftPad(this.getCallingMethodName(), 15, " "),
                StringUtils.leftPad(this.getCallingClassName(), 22, " "), message);
        fileWriterService.writeTextFile(config.getProperty("logger.file"), error);
        System.out.println(error);
    }

    private String getCallingMethodName() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    private String getCallingClassName() {
        return Thread.currentThread().getStackTrace()[4].getClassName();
    }
}
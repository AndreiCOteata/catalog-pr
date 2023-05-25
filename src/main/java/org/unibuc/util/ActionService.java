package org.unibuc.util;

import org.unibuc.config.AppConfig;
import org.unibuc.file.FileWriterService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActionService {
    private static ActionService instance;

    private final FileWriterService service = FileWriterService.getInstance();

    private final AppConfig config = AppConfig.getInstance();

    private ActionService() {
    }

    public static synchronized ActionService getInstance() {
        if (instance == null) {
            instance = new ActionService();
        }
        return instance;
    }

    public void saveAction(String actionName) {
        String[] headers = {"Timestamp", "Action"};
        String[][] data = {{LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), actionName}};
        this.service.writeCSVFile(config.getProperty("actions.folder"), headers, data);
    }
}

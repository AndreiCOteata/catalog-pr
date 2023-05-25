package org.unibuc.file;

import java.io.*;

public class FileWriterService {
    private static FileWriterService instance;

    private FileWriterService() {
    }

    public static synchronized FileWriterService getInstance() {
        if (instance == null) {
            instance = new FileWriterService();
        }
        return instance;
    }

    public void writeTextFile(String filePath, String content) {
        File file = new File(filePath);
        ensureFileExists(file);

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSVFile(String filePath, String[] headers, String[][] data) {
        File file = new File(filePath);
        ensureFileExists(file);

        try (FileWriter writer = new FileWriter(file, true)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String firstLine = reader.readLine();
                StringBuilder joiner = new StringBuilder();
                for (int index = 0; index < headers.length; index++) {
                    joiner.append(headers[index]);
                    if (index != headers.length - 1) {
                        joiner.append(",");
                    }
                }
                if (firstLine == null || !firstLine.contentEquals(joiner)) {
                    for (int i = 0; i < headers.length; i++) {
                        writer.append(headers[i]);
                        if (i < headers.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
            }

            // Write data
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    writer.append(row[i]);
                    if (i < row.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureFileExists(File file) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

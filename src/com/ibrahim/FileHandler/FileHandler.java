package com.ibrahim.FileHandler;

public interface FileHandler {
    String[] readLines(String filePath);
    boolean writeLines(String filePath, String[] lines);
    boolean appendLine(String filePath, String line);
}

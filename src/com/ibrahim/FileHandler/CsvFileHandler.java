package com.ibrahim.FileHandler;

public class CsvFileHandler implements FileHandler{

    @Override
    public String[] readLines(String filePath) {
        return new String[0];
    }

    @Override
    public boolean writeLines(String filePath, String[] lines) {
        return false;
    }

    @Override
    public boolean appendLine(String filePath, String line) {
        return false;
    }
}

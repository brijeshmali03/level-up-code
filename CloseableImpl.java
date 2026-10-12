package com.bm;

import java.io.Closeable;
import java.io.IOException;

public class CloseableImpl implements Closeable {

    private final String filePath;
    private boolean isOpen;

    public CloseableImpl(String filePath) {
        this.filePath = filePath;
        this.isOpen = true;
        System.out.println("Opening file: " + filePath);
    }

    public void readData() throws IOException {
        if (!isOpen) {
            throw new IOException("Cannot read from a closed file!");
        }
        System.out.println("Reading contents of " + filePath + "...");
    }

    @Override
    public void close() throws IOException {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing file: " + filePath);
        }
    }

    public static void main(String[] args) {
        try (CloseableImpl reader = new CloseableImpl("data.txt")) {
            reader.readData();
        } catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
        }
    }
}
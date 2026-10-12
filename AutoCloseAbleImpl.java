package com.bm;

public class AutoCloseAbleImpl implements AutoCloseable {
    
    private final String resourceName;
    private boolean isOpen;

    public AutoCloseAbleImpl(String resourceName) {
        this.resourceName = resourceName;
        this.isOpen = true;
        System.out.println("Opening resource: " + resourceName);
    }

    public void doWork() {
        if (!isOpen) {
            throw new IllegalStateException("Resource is closed!");
        }
        System.out.println("Working with resource: " + resourceName + "...");
    }

    @Override
    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing resource: " + resourceName);
        }
    }

    public static void main(String[] args) {
        try (AutoCloseAbleImpl resource = new AutoCloseAbleImpl("MyMockResource")) {
            resource.doWork();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
package com.bm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TrackResource {
    String owner();
    String priority() default "NORMAL";
}
@TrackResource(owner = "Alice", priority = "HIGH")
public class AnnotationDemo implements AutoCloseable {
    private final String resourceName;
    private boolean isOpen;
    public AnnotationDemo(String resourceName) {
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
    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing resource: " + resourceName);
        }
    }
    public static void main(String[] args) {
        Class<AnnotationDemo> clazz = AnnotationDemo.class;
        
        if (clazz.isAnnotationPresent(TrackResource.class)) {
            TrackResource tracker = clazz.getAnnotation(TrackResource.class);
            System.out.println("Annotation Metadata Found");
            System.out.println("Owner: " + tracker.owner());
            System.out.println("Priority: " + tracker.priority());
        }
        try (var resource = new AnnotationDemo("Database-X")) {
            resource.doWork();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
package com.bm;

public class EffectivelyFinalDemo {
    public static void main(String[] args) {
        String greeting = "Hello from an effectively final variable!";
        Runnable task = () -> {
            System.out.println(greeting);
        };
        task.run();
    }
}
package com.course.calypso.concurrency.thread_util;

public class UsingThreadClass {
    public static void main(String[] args) {
        new Thread( new Runnable() {
            @Override
            public void run() {
                // Some implementation here
            }
        } ).start();
    }
}

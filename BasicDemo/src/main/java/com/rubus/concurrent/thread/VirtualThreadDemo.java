package com.rubus.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadDemo {

    public static void main(String[] args) {
        createVirtualThread();
        useVirtualExecutor();
        useMultiVirtualThread();
    }

    public static void createVirtualThread() {
        Thread vThread = Thread.ofVirtual().start(() -> System.out.println("Hello from a virtual thread!"));

        //等待线程执行结束
        try {
            vThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void useVirtualExecutor() {
        // 创建一个虚拟线程执行器
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            //提交多个任务到虚拟线程执行器
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                executorService.submit(() -> System.out.println("Task " + taskId + " is running in virtual thread " + Thread.currentThread().getName()));
            }
        }
    }

    public static void useMultiVirtualThread() {
        int numberOfThreads = 10000;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = Thread.ofVirtual().start(() -> System.out.println("Virtual thread running"));
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("All virtual threads have finished");
    }

}

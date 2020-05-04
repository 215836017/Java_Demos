package com.demos.threadPool;

import java.util.concurrent.*;

public class TestThreadPool {

    class TestTask implements Runnable {

        private String name;
        private int timeCount = 0;

        public TestTask(String name) {
            this(name, 0);
        }

        public TestTask(String name, int timeCount) {
            this.name = name;
            this.timeCount = timeCount;
        }

        @Override
        public void run() {
            while (timeCount <= 5) {
                System.out.println("I'm " + name + ", timeCount = " + timeCount);

                timeCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TestThreadPool testThreadPool = new TestThreadPool();

//        testThreadPool.testFixedThreadPool();

//        testThreadPool.testSingleThreadExecutor();

//        testThreadPool.testCachedThreadPool();

//        testThreadPool.testScheduledThreadPool();

        testThreadPool.testThreadPoolExecutor();
    }

    private void testFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            TestTask task = new TestTask("Runnable" + i);
            System.out.println("add " + i + "th task to threadPool...");
            executorService.execute(task);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void testSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            TestTask task = new TestTask("Runnable" + i);
            System.out.println("add " + i + "th task to threadPool...");
            executorService.execute(task);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void testCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            TestTask task = new TestTask("Runnable" + i);
            System.out.println(System.currentTimeMillis() + " add " + i + "th task to threadPool...");
            executorService.execute(task);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void testScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 5; i++) {
            TestTask task = new TestTask("Runnable" + i);
            System.out.println(System.currentTimeMillis() + " add " + i + "th task to threadPool...");
            executorService.execute(task);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void testThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4,
                3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 5; i++) {
            TestTask task = new TestTask("Runnable" + i);
            System.out.println(System.currentTimeMillis() + " add " + i + "th task to threadPool...");
            threadPoolExecutor.execute(task);

            if(i == 3){
                threadPoolExecutor.shutdown();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        boolean shutdown = threadPoolExecutor.isShutdown();
//        threadPoolExecutor.shutdown();
    }
}

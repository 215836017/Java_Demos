package com.cakes.threadPool.test1;

import java.util.concurrent.*;

/**
*https://blog.csdn.net/jgteng/article/details/54411423
*/
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
        public String toString() {
            return "TestTask{" +
                    "name='" + name + '\'' +
                    ", timeCount=" + timeCount +
                    '}';
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

//        testThreadPool.testThreadPoolExecutor();

        TestTask task = null;
        try {
            System.out.println(task.toString());
        } catch (Exception e) {
            System.out.println("error : 0000000000000000");
        }
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
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3,
//                3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
//                new ThreadPoolExecutor.DiscardOldestPolicy());
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3,
//                3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
//                new ThreadPoolExecutor.AbortPolicy());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3,
                3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                rejectedExecutionHandler);
        for (int i = 0; i < 10; i++) {
            TestTask task = new TestTask("Runnable" + i);
            System.out.println(System.currentTimeMillis() + " add " + i + "th task to threadPool...");
            threadPoolExecutor.execute(task);

//            if (i == 3) {
//                threadPoolExecutor.shutdown();
//            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        boolean shutdown = threadPoolExecutor.isShutdown();
//        threadPoolExecutor.shutdown();
    }

    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("error 111111111111-- " + r.toString() + "  " + executor.toString());
//            executor.remove(r);

            if (r instanceof TestTask) {
                TestTask task = (TestTask) r;
                System.out.println("task is = " + task.toString());
            } else {
                System.out.println("error 2222222222222");
            }
        }
    };
}

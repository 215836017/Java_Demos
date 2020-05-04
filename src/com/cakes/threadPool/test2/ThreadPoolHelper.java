package com.cakes.threadPool.test2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolHelper {

    private static ThreadPoolHelper INSTANCE;
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolHelper() {
        initThreadPool();
    }

    public static ThreadPoolHelper getInstance() {
        if (null == INSTANCE) {
            synchronized (ThreadPoolHelper.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ThreadPoolHelper();
                }
            }
        }
        return INSTANCE;
    }

    private void initThreadPool() {
        threadPoolExecutor = new ThreadPoolExecutor(2, 4,
                3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                rejectedExecutionHandler);
    }

    public void releaseThreadPool() {
        if (null == threadPoolExecutor) {
            threadPoolExecutor.shutdown();
        }
        threadPoolExecutor = null;
    }

    public boolean addTask(BaseRunnable task) {
        if (null == task || threadPoolExecutor.isShutdown()) {
            return false;
        }
        threadPoolExecutor.execute(task);
        return true;
    }

    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (r instanceof BaseRunnable) {
                ((BaseRunnable) r).onRunFailed();
            }
        }
    };
}

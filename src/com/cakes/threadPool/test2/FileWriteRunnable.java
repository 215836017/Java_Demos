package com.cakes.threadPool.test2;

import com.cakes.utils.LogUtil;

public class FileWriteRunnable implements BaseRunnable {

    private final String TAG = "FileWriteRunnable.java  ";

    private String fileName;
    private String content;
    private FileHelper.OnFileOperationListener operationListener;

    public FileWriteRunnable(String fileName, String content, FileHelper.OnFileOperationListener operationListener) {
        LogUtil.logWithTime(TAG, "FileWriteRunnable() -- 1111111111111111111111");

        this.fileName = fileName;
        this.content = content;
        this.operationListener = operationListener;

    }

    @Override
    public void onRunFailed() {
        operationListener.onWriteFileFailed(fileName, "write ");
    }

    @Override
    public void run() {
        checkEnvironment();
    }

    private void checkEnvironment() {

    }

    @Override
    public String toString() {
        return "FileWirteRunnable{" +
                ", fileName='" + fileName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

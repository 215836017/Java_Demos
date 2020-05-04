package com.cakes.threadPool.test2;

public class FileReadRunnable implements BaseRunnable {

    private String fileName;
    private FileHelper.OnFileOperationListener operationListener;

    public FileReadRunnable(String fileName, FileHelper.OnFileOperationListener operationListener) {
        this.fileName = fileName;
        this.operationListener = operationListener;
    }

    @Override
    public void onRunFailed() {

    }

    @Override
    public void run() {


    }
}

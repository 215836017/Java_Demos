package com.cakes.threadPool.test2;

public class FileHelper {

    /**
     * Read data from file of given fileName
     *
     * @param fileName fileName to read, and must be an absolute path
     */
    public void readFile(String fileName, OnFileOperationListener operationListener) {
        FileReadRunnable readRunnable = new FileReadRunnable(fileName, operationListener);
        ThreadPoolHelper.getInstance().addTask(readRunnable);
    }

    /**
     * write content to the file of given fileName
     *
     * @param fileName fileName to write, and must be an absolute path
     * @param content  contents to be wrote
     */
    public static void writeFile(String fileName, String content, OnFileOperationListener operationListener) {
        FileWriteRunnable wirteRunnable = new FileWriteRunnable(fileName, content, operationListener);
        ThreadPoolHelper.getInstance().addTask(wirteRunnable);

    }

    public interface OnFileOperationListener {
        void onReadFileFinish(String fileName, String content);

        void onReadFileFailed(String fileName, String reason);

        void onWriteFileFinish(String fileName);

        void onWriteFileFailed(String fileName, String reason);
    }
}

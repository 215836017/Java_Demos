package com.cakes.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FileWriter extends Thread {

    private final String TAG = "FileWriter";
    private File file;
    private ConcurrentLinkedQueue<byte[]> queue;

    public FileWriter(String absolutePath) {
        init(absolutePath);
    }

    private void init(String path) {
        if (TextUtils.isEmpty(path)) {
            file = null;
            return;
        }

        file = new File(path);
        File dirs = file.getParentFile();
        if (!dirs.exists()) {
            dirs.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                file = null;
            }
        }
    }

    public void inputData(byte[] buffer) {
        queue.offer(buffer);
    }

    private boolean isWriting = false;

    public void stopWrite() {
        isWriting = false;
    }

    @Override
    public void run() {
        super.run();

        if (null == file) {
            System.out.println("create file failed...");
            return;
        }

        isWriting = true;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            while (isWriting) {
                byte[] poll = queue.poll();
                if (null == poll || poll.length == 0) {
                    sleep(100);
                } else {
                    fileOutputStream.write(poll);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            isWriting = false;
        }

    }
}

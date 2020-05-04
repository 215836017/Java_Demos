package com.demos.files;

import java.io.*;

public class FileProcesser {

    private static FileProcesser instance;

    public static FileProcesser getInstance() {
        if (null == instance) {
            instance = new FileProcesser();
        }
        return instance;
    }

    public void wirteMsgInFile(String msg, String fileAbsolutePath) {
        if (TextUtils.isEmpty(msg) || TextUtils.isEmpty(fileAbsolutePath)) {
            return;
        }
        File file = new File(fileAbsolutePath);
        if (null == file) {
            return;
        }
        File dir = file.getParentFile();
        if (null == dir) {
            return;
        }
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            wirteMsg(msg, file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wirteMsg(final String msg, final File file) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                FileOutputStream fileOutputStream = null;
                BufferedWriter out = null;

                try {
                    fileOutputStream = new FileOutputStream(file, true);
                    out = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    out.write(msg);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != out) {
                            out.close();
                            out = null;
                        }

                        if (null != fileOutputStream) {
                            fileOutputStream.close();
                            fileOutputStream = null;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}

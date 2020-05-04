package com.cakes.files;

import java.io.*;

public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance() {
        if (null == instance) {
            instance = new FileManager();
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

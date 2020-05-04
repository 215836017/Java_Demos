package com.cakes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

    public static boolean isPrintLog = true;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSS");

    public static void log(String tag, String msg) {

        if (!isPrintLog) {
            return;
        }

        if (!tag.endsWith(" ")) {
            tag += " ";
        }
        log(tag + msg);
    }

    public static void logWithTime(String tag, String msg) {

        if (!isPrintLog) {
            return;
        }

        if (!tag.endsWith(" ")) {
            tag += " ";
        }
        logWithTime(tag + msg);
    }

    public static void log(String msg) {
        if (!isPrintLog) {
            return;
        }
        System.out.println(msg);
    }

    public static void logWithTime(String msg) {
        if (!isPrintLog) {
            return;
        }
        System.out.println(sdf.format(new Date()) + "  " + msg);
    }


}

package com.kinstalk.android.utils;

import android.util.Log;

/**
 * Created by siqing on 16/1/20.
 */
public class LogUtils {

    public static boolean isLog = true;


    public static void D(String TAG, String msg) {
        log(TAG, msg, Log.DEBUG);
    }

    public static void W(String TAG, String msg) {
        log(TAG, msg, Log.WARN);
    }

    public static void E(String TAG, String msg) {
        log(TAG, msg, Log.ERROR);
    }


    private static void log(String TAG, String msg, int logType) {
        if (isLog) {
            switch (logType) {
                case Log.VERBOSE:
                    Log.v(TAG, msg);
                    break;
                case Log.DEBUG:
                    Log.d(TAG, msg);
                    break;
                case Log.INFO:
                    Log.i(TAG, msg);
                    break;
                case Log.WARN:
                    Log.w(TAG, msg);
                    break;
                case Log.ERROR:
                    Log.e(TAG, msg);
                    break;
                default:
                    break;
            }
        }
    }


    public static void logE(String TAG, String msg) {
        Log.e(TAG, logWithThreadInfo(msg));
    }

    private static String logWithThreadInfo(String msg) {
        return "[" + Thread.currentThread().getName() + "][" + msg + "]";
    }
}

package io.valuesfeng.picker.utils;

import android.util.Log;

/**
 * Created by 11355 on 2017/12/2.
 */

public class LogUtils {
    private static boolean isLog=false;
    public static void setDebug(boolean b) {
        isLog=b;
    }
    public static void e(String tag, String msg) {

        if (isLog){
            Log.e(tag, "onResponse: "+msg );
        }

    }
}

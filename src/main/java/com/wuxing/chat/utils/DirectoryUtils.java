package com.wuxing.chat.utils;

/**
 * Created by wuxing on 2017-1-3.
 */
public class DirectoryUtils {
    public static String getPath(String uuidFileName) {
        int code1 = uuidFileName.hashCode();
        int d1 = code1 & 0xf;
        int code2 = code1 >>> 4;
        int d2 = code2 & 0xf;
        return "/" + d1 + "/" + d2;
    }
}

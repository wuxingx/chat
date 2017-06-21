package com.wuxing.chat.utils;

import java.util.UUID;

/**
 * Created by wuxing on 2016-12-27.
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

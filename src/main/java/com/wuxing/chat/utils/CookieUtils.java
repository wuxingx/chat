package com.wuxing.chat.utils;

import javax.servlet.http.Cookie;

/**
 * Created by wuxing on 2016-12-19.
 */
public class CookieUtils {

    public static Cookie cookie(Cookie[] cookies,String name) {
        if (cookies == null) {
            return null;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
            return null;
        }
    }
}

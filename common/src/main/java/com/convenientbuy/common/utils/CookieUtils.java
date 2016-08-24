package com.convenientbuy.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by bonismo@hotmail.com
 * 下午10:32 on 16/8/24.
 */
public class CookieUtils {

    /**
     * 获取 Cookie 值, 不进行编码
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 根据 Cookie 值,确定是否编码
     * @param request
     * @param cookieName Cookie
     * @param isDecoder 是否编码
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null) {
            return null;
        }
        String resultValue = null;
        try {
            for (int i = 0; i < cookies.length; i++) {
                if (isDecoder) {
                    resultValue = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                } else {
                    resultValue = cookies[i].getValue();
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultValue;
    }


    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null) {
            return null;
        }
        String resultValue = null;
        try {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    resultValue = URLDecoder.decode(cookies[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultValue;
    }
}

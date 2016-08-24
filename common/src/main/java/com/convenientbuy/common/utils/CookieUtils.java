package com.convenientbuy.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    /**
     * 根据传入编码集, 进行 Cookie 编码
     * @param request
     * @param cookieName
     * @param encodeString 编码集
     * @return
     */
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

    /**
     * 设置 Cookie 值, 不设置时长,关闭浏览器即失效,不编码
     * @param request
     * @param response
     * @param cookieName Cookie 名字
     * @param cookieValue Cookie 值
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置 Cookie 值, 指定时间内生效,不编码
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage 生效时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, false);
    }

    /**
     *
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param isEncode
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }
}

package com.convenientbuy.common.utils;

import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
     *
     * @param request
     * @param cookieName
     * @param isDecoder
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


}

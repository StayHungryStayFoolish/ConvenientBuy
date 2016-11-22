package com.convenientbuy.portal.interceptor;

import com.convenientbuy.common.utils.CookieUtils;
import com.convenientbuy.pojo.CbUser;
import com.convenientbuy.portal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bonismo@hotmail.com
 * 上午7:08 on 16/10/21.
 * <p>
 * 系统登录拦截器,在特殊应用场景进行拦截.
 * 该场景应用在 SSO 单点登录系统,进行用户登录拦截
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserServiceImpl userService;

    /**
     * 在拦截器处理之前进行拦截用户登录
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(httpServletRequest, "CON_TOKEN");
        CbUser user = userService.getUserByToken(token);
        if (null == user) {
            httpServletResponse.sendRedirect(userService.SSO_DOMAIN_BASE_USRL + userService.SSO_PAGE_LOGIN + "?redirect" + httpServletRequest.getRequestURL());
            return false;
        }
        httpServletRequest.setAttribute("user", user);
        // 返回值确定 Handler 是否执行拦截. true 执行 / false 不执行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // handler执行之后，返回ModelAndView之前
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 返回ModelAndView之后。
        //响应用户之后。
    }
}

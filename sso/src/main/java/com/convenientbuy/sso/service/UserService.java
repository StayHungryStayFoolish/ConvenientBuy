package com.convenientbuy.sso.service;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.pojo.CbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bonismo@hotmail.com
 * 下午5:28 on 16/12/17.
 */
public interface UserService {

    /**
     * 用户信息校验
     *
     * @param content 1.username 2.phone 3.email
     * @param type    1.2.3
     * @return
     */
    Result checkData(String content, Integer type);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    Result createUser(CbUser user);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据 token 获取用户信息
     *
     * @param token
     * @return
     */
    Result getUserByToken(String token);
}

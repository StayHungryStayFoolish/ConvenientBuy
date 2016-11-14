package com.convenientbuy.portal.service;

import com.convenientbuy.pojo.CbUser;

/**
 * Created by bonismo@hotmail.com
 * 下午9:55 on 16/11/14.
 */
public interface UserService {

    /**
     * 获取用户的 Token
     *
     * @param token
     * @return
     */
    CbUser getUserByToken(String token);
}

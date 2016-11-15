package com.convenientbuy.portal.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.HttpClientUtil;
import com.convenientbuy.pojo.CbUser;
import com.convenientbuy.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by bonismo@hotmail.com
 * 下午11:57 on 16/11/15.
 */
@Service
public class UserServiceImpl implements UserService {

    // 单点登录系统基础 URL
    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    // 单点登录系统域名
    @Value("${SSO_DOMAIN_BASE_USRL}")
    public String SSO_DOMAIN_BASE_USRL;
    // 根据用户信息获取 Token URL
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;

    // 在 LoginInterceptor 调用
    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;

    /**
     * 通过 Token 获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public CbUser getUserByToken(String token) {
        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            Result result = Result.formatToPojo(json, CbUser.class);
            if (result.getStatus() == 200) {
                CbUser user = (CbUser) result.getData();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

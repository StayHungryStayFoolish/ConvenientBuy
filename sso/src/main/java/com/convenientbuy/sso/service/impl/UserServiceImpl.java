package com.convenientbuy.sso.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.mapper.CbUserMapper;
import com.convenientbuy.pojo.CbUser;
import com.convenientbuy.sso.dao.JedisClient;
import com.convenientbuy.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bonismo@hotmail.com
 * 下午5:32 on 16/12/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CbUserMapper mapper;

    @Autowired
    private JedisClient jedisClient;

    // 用户 session 在 redis 中保存的 key
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    // Session 过期时间 30 分钟
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public Result checkData(String content, Integer type) {
        return null;
    }

    @Override
    public Result createUser(CbUser user) {
        return null;
    }

    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Result getUserByToken(String token) {
        return null;
    }
}

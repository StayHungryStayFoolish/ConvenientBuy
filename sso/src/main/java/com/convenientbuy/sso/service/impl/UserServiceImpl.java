package com.convenientbuy.sso.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.mapper.CbUserMapper;
import com.convenientbuy.pojo.CbUser;
import com.convenientbuy.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

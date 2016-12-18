package com.convenientbuy.sso.service.impl;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.CookieUtils;
import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.mapper.CbUserMapper;
import com.convenientbuy.pojo.CbUser;
import com.convenientbuy.pojo.CbUserExample;
import com.convenientbuy.sso.dao.JedisClient;
import com.convenientbuy.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    /**
     * 根据内容进行用户信息校验
     *
     * @param content 1.username 2.phone 3.email
     * @param type    1.2.3
     * @return
     */
    @Override
    public Result checkData(String content, Integer type) {
        // 创建查询条件
        CbUserExample example = new CbUserExample();
        CbUserExample.Criteria criteria = example.createCriteria();
        //对数据进行校验：1、2、3分别代表username、phone、email
        //用户名校验
        if (1 == type) {
            criteria.andUsernameEqualTo(content);
            //电话校验
        } else if (2 == type) {
            criteria.andPhoneEqualTo(content);
            //email校验
        } else {
            criteria.andEmailEqualTo(content);
        }
        //执行查询
        List<CbUser> list = mapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return Result.ok(true);
        }
        return Result.ok(false);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public Result createUser(CbUser user) {
        user.setUpdated(new Date());
        user.setCreated(new Date());
        // Spring 框架 MD5 加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        mapper.insert(user);
        return Result.ok();
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        CbUserExample example = new CbUserExample();
        CbUserExample.Criteria criteria = example.createCriteria();
        // 登录前判断
        // 用户登录校验
        criteria.andUsernameEqualTo(username);
        List<CbUser> list = mapper.selectByExample(example);
        if (null == list || list.size() == 0) {
            return Result.build(400, "用户名或密码错误");
        }
        CbUser user = list.get(0);
        // 密码校验
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return Result.build(400, "用户名或密码错误");
        }
        // 使用 UUID 来生成 token
        String token = UUID.randomUUID().toString();
        // token 中不保存密码,安全性更高
        user.setPassword(null);

        // 用户信息写入 Redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJSON(user));
        // 设置 Redis 有效期
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

        CookieUtils.setCookie(request, response, "CON_TOKEN", token);
        // 携带 token 返回
        return Result.ok(token);
    }

    @Override
    public Result getUserByToken(String token) {
        return null;
    }
}

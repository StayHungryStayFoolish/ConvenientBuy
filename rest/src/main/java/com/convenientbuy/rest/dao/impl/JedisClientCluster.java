package com.convenientbuy.rest.dao.impl;

import com.convenientbuy.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * Created by bonismo@hotmail.com
 * 下午7:56 on 16/11/8.
 */
public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster jedisCluster;
}

package com.convenientbuy.order.dao;

/**
 * Created by bonismo@hotmail.com
 * 下午7:56 on 16/11/8.
 */
public interface JedisClient {

    String get(String key);

    String set(String key, String value);

    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    long incr(String key);

    // 设置有效时间
    long expire(String key, int second);

    // 失效时间
    long ttl(String key);

    //当数据库发生改变时，清空缓存，以便更新redis数据
    long del(String key);

    //当数据库发生改变时，清空缓存，以便更新redis数据
    long hdel(String hkey, String key);

}

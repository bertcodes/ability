package com.ability.seckill.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Copyright (C): :
 * FileName: RedisService
 *
 * @author caobo
 * @create 2019-1-1 23:52
 * @since 1.0.0
 * Description:
 */
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     *
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     * 设置对象
     */
    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0){
                return false;
            }
            //生成真正的key
            String realKey = prefix.getPrefix()+key;
            int secondes = prefix.expireSeconds();
            if(secondes <= 0){
                jedis.set(realKey,str);
            }else{
                jedis.setex(realKey,secondes,str);
            }
          return true;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     * 获取单个对象
     */
    public <T> T  get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            if(realKey == null || realKey.length() <= 0 || clazz == null){
                return null;
            }
            String str = jedis.get(realKey);
            T t = stringtoBean(str,clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param prefix
     * @param key
     * @return
     * 判断key是否存在
     */
    public boolean exists(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            if(realKey == null|realKey.length() <= 0){
                return false;
            }
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param prefix
     * @param key
     * @return
     * 增加值
     */
    public Long  incr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
             String realKey = prefix.getPrefix()+key;
             return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param prefix
     * @param key
     * @return
     * 减少值
     */
    public Long  decr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    private <T> T stringtoBean(String str, Class<T> clazz) {
        if(str == null || str.length() <=0 || clazz == null){
            return null;
        }
        if(clazz == int.class){
            return (T) Integer.valueOf(str);
        }else if(clazz == long.class){
            return (T) Long.valueOf(str);
        }else if(clazz == String.class){
            return (T) str;
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if(jedis !=null ){
            jedis.close();
        }
    }

    private <T> String beanToString(T value) {
        if(value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class){
            return ""+value;
        }else if(clazz == long.class){
            return ""+value;
        }else if(clazz == String.class){
            return (String)value;
        }else {
            return JSON.toJSONString(value);
        }
    }
}

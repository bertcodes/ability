package com.ability.seckill.redis;

/**
 * Copyright (C): :
 * FileName: BaseKey
 *
 * @author caobo
 * @create 2019-1-1 23:40
 * @since 1.0.0
 * Description:
 */
public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix) {//0代表永不过期
       this(0,prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {//0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }

}

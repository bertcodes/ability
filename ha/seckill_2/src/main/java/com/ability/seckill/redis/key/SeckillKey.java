package com.ability.seckill.redis.key;

import com.ability.seckill.model.SeckillUser;
import com.ability.seckill.redis.BasePrefix;

import javax.swing.plaf.PanelUI;

/**
 * Copyright (C): :
 * FileName: SeckillKey
 *
 * @author caobo
 * @create 2019-1-3 14:57
 * @since 1.0.0
 * Description:
 */
public class SeckillKey extends BasePrefix{
    private static final int TOKEN_EXPIRE = 3600*24*1;

    private SeckillKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static SeckillKey token = new SeckillKey(TOKEN_EXPIRE,"tk");

}

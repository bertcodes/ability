package com.ability.seckill.redis.key;

import com.ability.seckill.redis.BasePrefix;

/**
 * Copyright (C): :
 * FileName: UserKey
 *
 * @author caobo
 * @create 2019-1-2 10:40
 * @since 1.0.0
 * Description:
 */
public class UserKey extends BasePrefix{

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");


}

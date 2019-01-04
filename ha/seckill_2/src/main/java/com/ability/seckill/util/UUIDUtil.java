package com.ability.seckill.util;

import java.util.UUID;

/**
 * Copyright (C): :
 * FileName: UUIDUtil
 *
 * @author caobo
 * @create 2019-1-3 15:03
 * @since 1.0.0
 * Description:
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}

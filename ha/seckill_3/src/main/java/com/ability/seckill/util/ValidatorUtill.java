package com.ability.seckill.util;

import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C): :
 * FileName: ValidatorUtill
 *
 * @author caobo
 * @create 2019-1-3 16:40
 * @since 1.0.0
 * Description:
 */
public class ValidatorUtill {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(Strings.isNullOrEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
}

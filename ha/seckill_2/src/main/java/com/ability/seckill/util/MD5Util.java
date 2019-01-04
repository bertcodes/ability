package com.ability.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.net.URLEncoder;

/**
 * Copyright (C): :
 * FileName: MD5Util
 *
 * @author caobo
 * @create 2019-1-2 16:02
 * @since 1.0.0
 * Description:
 */
public class MD5Util {

    private final static String salt = "1a2b3c4d5e";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2) +salt.charAt(5) + inputPass+ salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }
    public static String formPassToDbPass(String inputPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2) +salt.charAt(5) + inputPass+ salt.charAt(4);
        return md5(str);
    }
    public static String inputPassToDbPass(String inputPass,String saltDB){
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDbPass(formPass,saltDB);
    }

    public static void main(String[] args) {

        System.out.println(inputPassToFormPass("123456"));
//       System.out.println(formPassToDbPass("3e53085a51505696bc122ff59ab8b478","1a2b3c4d5e"));
//        System.out.println(inputPassToDbPass("123456","1a2b3c4d5e"));//7d5f2d6f9096b27be015602cf046d7ce
    }

}

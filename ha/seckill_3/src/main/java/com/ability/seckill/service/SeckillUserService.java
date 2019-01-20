package com.ability.seckill.service;

import com.ability.seckill.dao.SeckillUserDao;
import com.ability.seckill.exception.GolbalException;
import com.ability.seckill.model.SeckillUser;
import com.ability.seckill.redis.RedisService;
import com.ability.seckill.redis.key.SeckillKey;
import com.ability.seckill.result.CodeMsg;
import com.ability.seckill.util.MD5Util;
import com.ability.seckill.util.UUIDUtil;
import com.ability.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse; /**
 * Copyright (C): :
 * FileName: SeckillService
 *
 * @author caobo
 * @create 2019-1-3 13:57
 * @since 1.0.0
 * Description:
 */
@Service
public class SeckillUserService {

    @Autowired
    private SeckillUserDao userDao;
    @Autowired
    private RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null){
            throw new GolbalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPasswprd = loginVo.getPassword();
        //验证手机号码是否存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GolbalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //校验密码
        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(formPasswprd,dbSalt);
        if(!calcPass.equals(dbPass)){
            throw new GolbalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        //生成cookie
        setCookie(response,token,user);
        return true;
    }

    private boolean setCookie(HttpServletResponse response, String token, SeckillUser user) {
        redisService.set(SeckillKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(SeckillKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    public SeckillUser getById(long id) {
        return userDao.getById(id);
    }

    public SeckillUser getByToken(HttpServletResponse response, String token) {
        if(token == null){
            return null;
        }
        SeckillUser user = redisService.get(SeckillKey.token,token,SeckillUser.class);
        if(user != null){
            setCookie(response,token,user);

        }
        return user;
    }
}

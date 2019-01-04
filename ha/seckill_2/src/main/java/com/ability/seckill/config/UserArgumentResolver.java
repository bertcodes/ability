package com.ability.seckill.config;

import com.ability.seckill.model.SeckillUser;
import com.ability.seckill.service.SeckillUserService;
import com.google.common.base.Strings;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C): :
 * FileName: UserArgumentResolver
 *
 * @author caobo
 * @create 2019-1-3 17:16
 * @since 1.0.0
 * Description:
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

    @Autowired
    private SeckillUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == SeckillUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(SeckillUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request,SeckillUserService.COOKIE_NAME_TOKEN);

        String token = Strings.isNullOrEmpty(paramToken)? cookieToken : paramToken;

        return userService.getByToken(response,token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookieNameToken)){
                return cookie.getValue();
            }
        }
        return null;
    }


}

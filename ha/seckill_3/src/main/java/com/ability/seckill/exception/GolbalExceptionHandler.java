package com.ability.seckill.exception;

import com.ability.seckill.result.CodeMsg;
import com.ability.seckill.result.Result;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright (C): :
 * FileName: GolbalExceptionHandler
 *
 * @author caobo
 * @create 2019-1-3 9:41
 * @since 1.0.0
 * Description:
 */
@ControllerAdvice
@ResponseBody
public class GolbalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GolbalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        if(e instanceof GolbalException){
            return Result.error(((GolbalException) e).getCm());
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.findArgs(msg));
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }

}

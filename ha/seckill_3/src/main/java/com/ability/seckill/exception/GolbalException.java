package com.ability.seckill.exception;

import com.ability.seckill.result.CodeMsg;

/**
 * Copyright (C): :
 * FileName: GolbalException
 *
 * @author caobo
 * @create 2019-1-3 14:17
 * @since 1.0.0
 * Description:
 */
public class GolbalException extends RuntimeException{

    private CodeMsg cm;

    public GolbalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}

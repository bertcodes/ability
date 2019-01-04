package com.ability.seckill.validator;

import com.ability.seckill.util.ValidatorUtill;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C): :
 * FileName: IsMobileValidator
 *
 * @author caobo
 * @create 2019-1-3 16:32
 * @since 1.0.0
 * Description:
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String>{

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required){
            return ValidatorUtill.isMobile(value);
        }else{
            if(Strings.isNullOrEmpty(value)){
                return true;
            }else{
                return ValidatorUtill.isMobile(value);
            }
        }
    }
}

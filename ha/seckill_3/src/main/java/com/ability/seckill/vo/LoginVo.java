package com.ability.seckill.vo;

import com.ability.seckill.validator.IsMobile;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Copyright (C): :
 * FileName: LoginVo
 *
 * @author caobo
 * @create 2019-1-2 17:40
 * @since 1.0.0
 * Description:
 */
public class LoginVo {

    @NotBlank(message = "手机号码不能为空")
    @IsMobile
    private String mobile;

    @NotBlank
    @Length(min = 32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

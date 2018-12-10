package com.damon.cloud.user.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * FileName: User
 *
 * @author caobo
 * @create 2018-8-12 16:43
 * @since 1.0.0
 * Description:
 */
@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;
}

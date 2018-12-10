package com.damon.cloud.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * FileName: User
 *
 * @author caobo
 * @create 2018-8-12 14:06
 * @since 1.0.0
 * Description:
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;

}

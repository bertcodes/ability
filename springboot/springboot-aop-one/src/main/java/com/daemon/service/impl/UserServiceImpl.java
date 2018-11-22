package com.daemon.service.impl;

import com.daemon.annotation.OperationLogDetail;
import com.daemon.enums.OperationType;
import com.daemon.enums.OperationUnit;
import com.daemon.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    @Override
    public String findUserName(String tel) {
        System.out.println("tel:" + tel);
        return "zhangsan";
    }
}

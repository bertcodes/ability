package com.daemon.controller;

import com.daemon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param tel 手机号
     * @return userName
     */
    @ResponseBody
    @RequestMapping("/findUserNameByTel")
    public String findUserNameByTel(@RequestParam("tel") String tel){
        return userService.findUserName(tel);
    }
}

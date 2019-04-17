package com.scda.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: liuqi
 * @Date: 2019/3/11 14:38
 * @Description: 登录跳转，动静分离项目不需要这个类，需要自己重新
 */
@Controller
public class LoginVewController {
    /**
     * 登录跳转
     * @return
     */
    @RequestMapping("/login")
    public String loginView(){
        return "login";
    }
}

package com.scda.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuqi
 * @Date: 2018/11/9 16:46
 * @Description:
 */
@RestController
@RequestMapping("/demo")
@RefreshScope
public class DemoController {
    @Value("${spring.redis.database}")
    private String database;
    @GetMapping("/test")
    public String test(){
        return database;
    }
}

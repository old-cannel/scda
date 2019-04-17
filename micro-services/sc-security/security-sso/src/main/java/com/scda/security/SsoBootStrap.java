package com.scda.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: liuqi
 * @Date: 2019/1/21 15:27
 * @Description: 单点登录服务
 */
@SpringCloudApplication
@ComponentScan("com.scda")
@EnableTransactionManagement
@MapperScan(basePackages = "com.scda.*.mapper*")
@EnableEurekaClient
public class SsoBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoBootStrap.class).web(true).run(args);
    }
}

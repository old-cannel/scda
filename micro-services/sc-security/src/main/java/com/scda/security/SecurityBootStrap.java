package com.scda.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 17:08
 * @Description:
 */
@SpringCloudApplication
@ComponentScan("com.scda")
@EnableTransactionManagement
@MapperScan(basePackages = "com.scda.*.mapper*")
@EnableEurekaClient
public class SecurityBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SecurityBootStrap.class).web(true).run(args);
    }
}

package com.scda.security.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:29
 * @Description:
 */
@SpringCloudApplication
@ComponentScan("com.scda")
@EnableTransactionManagement
@MapperScan(basePackages = "com.scda.*.mapper*")
@EnableEurekaClient
public class Test2BootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Test2BootStrap.class).web(true).run(args);
    }

}

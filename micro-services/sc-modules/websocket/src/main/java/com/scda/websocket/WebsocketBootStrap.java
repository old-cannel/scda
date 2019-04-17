package com.scda.websocket;

import com.scda.security.config.WebSecurityConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: liuqi
 * @Date: 2019/03/11 13:38
 * @Description: websocket 服务
 */
@SpringCloudApplication
@ComponentScan("com.scda")
@EnableTransactionManagement
@MapperScan(basePackages = "com.scda.*.mapper*")
@EnableEurekaClient
public class WebsocketBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WebsocketBootStrap.class).web(true).run(args);
    }

}

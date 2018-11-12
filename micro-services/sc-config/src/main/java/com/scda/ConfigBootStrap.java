package com.scda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: liuqi
 * @Date: 2018/11/9 14:24
 * @Description:
 */
@SpringCloudApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigBootStrap {
    public static void main(String[] args){
        SpringApplication.run(ConfigBootStrap.class,args);
    }
}

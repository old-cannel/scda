package com.scda;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liuqi
 * @Date 2018-11-09.
 * @describe
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaBootStrap.class).web(true).run(args);
    }
}

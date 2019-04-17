package com.scda;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: liuqi
 * @Date: 2018/11/14 14:29
 * @Description: 文件上传服务
 */
@SpringCloudApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@ComponentScan("com.scda")
@EnableEurekaClient
public class UploadBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UploadBootStrap.class).web(true).run(args);
    }

}

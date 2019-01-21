package com.scda.security.utils;

import com.scda.common.test.ApplicationTest;
import com.scda.security.test.Test1BootStrap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: liuqi
 * @Date: 2019/1/19 09:41
 * @Description: 密码加密工具
 */
@SpringBootTest(classes = Test1BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestBCryptPasswordEncoder extends ApplicationTest {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testEncoder() {
        log.info("加密密码:{}",bCryptPasswordEncoder.encode("123456"));
    }
}

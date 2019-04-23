package com.scda.security.test;

import com.scda.common.test.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: liuqi
 * @Date: 2019/4/23 14:27
 * @Description: 测试基类-带会话
 * 测试方法直接加注解指定用户名即可
 * @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
 */
@Slf4j
public class BaseApplicationTest extends ApplicationTest {

    @Autowired
    private TestAuthenticationSuccess testAuthenticationSuccess;

    @Override
    public void init() {
        super.init();
        testAuthenticationSuccess.token();
        log.debug("测试会话登录成功");
    }
}

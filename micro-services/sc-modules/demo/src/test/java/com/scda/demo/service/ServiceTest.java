package com.scda.demo.service;

import com.scda.business.common.vo.demo.DemoVo;
import com.scda.demo.DemoBootStrap;
import com.scda.security.test.BaseApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: liuqi
 * @Date: 2019/4/23 10:24
 * @Description: 服务测试
 */
@SpringBootTest(classes = DemoBootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
//事务回滚需要加下面这个注解
@Transactional(transactionManager = "transactionManager")
public class ServiceTest extends BaseApplicationTest {

    @Autowired
    private DemoService demoService;

    private DemoVo create() {
        DemoVo demoVo = new DemoVo();
        demoVo.setCode("1");
        demoVo.setName("hehe");
        return demoVo;
    }

    @Test
//    @Rollback(value = false)
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void testUpdate() {
        demoService.update(create());
    }


}

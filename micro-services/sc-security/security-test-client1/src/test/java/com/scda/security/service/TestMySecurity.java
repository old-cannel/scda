package com.scda.security.service;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.test.ApplicationTest;
import com.scda.security.test.BaseApplicationTest;
import com.scda.security.test.Test1BootStrap;
import com.scda.security.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: liuqi
 * @Date: 2019/1/18 14:28
 * @Description: 测试自定义安全服务
 */
@SpringBootTest(classes = Test1BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j//事务回滚需要加下面这个注解
@Transactional(transactionManager = "transactionManager")
public class TestMySecurity extends BaseApplicationTest {
    @Autowired
    private MySecurityService mySecurityService;

    @Test
    public void loadByUserName() {

        SysUserVo sysUserVo = mySecurityService.loadByUserName("admin");
        Assert.assertNotNull(sysUserVo);
        log.debug("用户admin信息：{}", JSONObject.toJSONString(sysUserVo));
    }


}

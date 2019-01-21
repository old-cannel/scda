package com.scda.security.utils;

import com.scda.common.test.ApplicationTest;
import com.scda.common.utils.RedisUtil;
import com.scda.security.service.MySecurityService;
import com.scda.security.test.Test1BootStrap;
import com.scda.security.vo.SysRoleMenuVo;
import com.scda.security.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/19 16:43
 * @Description: 测试 redis工具类
 */
@SpringBootTest(classes = Test1BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestRedisUtil extends ApplicationTest {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MySecurityService mySecurityService;

    @Test
    public void test() {
        List<SysRoleMenuVo> sysRoleMenuVos=mySecurityService.loadAllRroleMenuIds();
        Assert.assertEquals(true, redisUtil.hset("test","1",sysRoleMenuVos));
        log.info("加载redis数据:{}",redisUtil.hget("test","1"));
    }

}

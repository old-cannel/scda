package com.scda.common.utils;

import com.scda.common.test.ApplicationTest;
import com.scda.security.test.Test1BootStrap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Auther: liuqi
 * @Date: 2019/1/16 11:07
 * @Description:
 */
@SpringBootTest(classes = Test1BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RedisUtilTest extends ApplicationTest {
    @Autowired
    RedisUtil redisUtil;
    @Test
    public void testSet(){
        Assert.assertEquals(true,redisUtil.set("1","2"));
    }
    @Test
    public void testExpire(){
        Assert.assertEquals(true,redisUtil.expire("1",100));
    }
    @Test
    public void testGetExpire(){
        log.info("{}",redisUtil.getExpire("1"));
    }

}

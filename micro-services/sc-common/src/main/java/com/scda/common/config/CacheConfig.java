package com.scda.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther: liuqi
 * @Date: 2019/2/13 11:35
 * @Description: 缓存配置
 * 基于redis
 */
@Configuration
@EnableCaching
public class CacheConfig {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(60*10);//默认过期时间十分钟
        cacheManager.setLoadRemoteCachesOnStartup(true); // 启动时加载远程缓存
        cacheManager.setUsePrefix(true); //是否使用前缀生成器
        // 这里可进行一些配置 包括默认过期时间 每个cacheName的过期时间等 前缀生成等等
        return cacheManager;
    }
}

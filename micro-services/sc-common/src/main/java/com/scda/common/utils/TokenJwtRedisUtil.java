package com.scda.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.scda.common.response.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: liuqi
 * @Date: 2019/1/16 13:48
 * @Description: token 封装类
 * 基于jwt、redis实现token会话过期、刷新
 *  * 会话流程
 *  * 1.用户客户端登录成功，后台服务签发token
 *  * 2.客户端收到token,用Base64进行解码jwt token的.第一部分，记录validTime时间戳
 *  * 3.客户端发送请求前判断validTime时间戳跟当前时间比较，如果相差在JwtUtils.TIME_OUT时间内，通过传入原token获取新token,再用新token获取发送请求
 *  * 4.如果需要对废弃的token进行回收，可以服务端可以将token放到redis里面，有效期设置为JwtUtils.TIME_OUT，每次刷新token，删除redis对应的token,会话判断的时候先判断redis对应的token是否存在
 *  * 5.以上如果客户端请求不需要会话的请求不要带令牌请求头请求，否则会照样进行登录认证
 */
@Component
public class TokenJwtRedisUtil {

    private static String TOKEN_HASH_KEY = "token_jwt";


    @Autowired
    RedisUtil redisUtil;

    /**
     * 创建token
     * @param jsonObject
     * @return
     */
    public String createToken(JSONObject jsonObject) {
        String token = JwtUtils.tokenWithTime(jsonObject);
        if (!redisUtil.hset(TOKEN_HASH_KEY, token, jsonObject, JwtUtils.TIME_OUT)) {
            throw new RuntimeException("缓存服务写入异常");
        }
        return token;
    }

    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    public String refreshToken(String oldToken) {
        String newToken = JwtUtils.refreshToken(oldToken);
        JSONObject newInfo = JwtUtils.verifyWithTime(newToken);
        redisUtil.hdel(TOKEN_HASH_KEY,oldToken);
       if (!redisUtil.hset(TOKEN_HASH_KEY, newToken, newInfo, JwtUtils.TIME_OUT)) {
            throw new RuntimeException("缓存服务写入异常");
        }
        return newToken;
    }

    /**
     * 删除token
     * @param token
     */
    public void deleteToken(String token){
        redisUtil.hdel(TOKEN_HASH_KEY,token);
    }

    /**
     * 解析并验证token
     * @param token
     * @return
     */
    public JSONObject decodeVerifyToken(String token){
        JSONObject info=(JSONObject) redisUtil.hget(TOKEN_HASH_KEY,token);
        if(info!=null){
            return JwtUtils.verifyWithTime(token);
        }
        throw new RuntimeException(ResponseEnum.NO_LOGIN.getMessage());
    }

}

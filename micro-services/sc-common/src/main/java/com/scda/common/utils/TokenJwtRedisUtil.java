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
 *  * 2.客户端收到token,调用用户信息接口获取用户内容 token的.第一部分，记录timeOut时间戳（客户端）
 *  * 3.客户端发送请求前判断timeOut时间戳跟当前时间比较，如果当前时间大于timeOut，通过传入原token获取新token,再用新token获取发送请求（客户端）
 *  * 4.如果需要对废弃的token进行回收，服务端可以将token放到redis里面，有效期设置为JwtUtils.TIME_OUT，每次刷新token，删除redis对应的token,会话判断的时候先判断redis对应的token是否存在
 *  * 5.以上如果客户端请求不需要会话的请求不要带令牌请求头请求，否则会照样进行登录认证（客户端）
 */
@Component
public class TokenJwtRedisUtil {

    public static String TOKEN_KEY = "token_jwt";


    @Autowired
    RedisUtil redisUtil;

    /**
     * 创建token
     * @param jsonObject
     * @return
     */
    public String createToken(JSONObject jsonObject) {
        String token = JwtUtils.tokenWithTime(jsonObject);
        if (!redisUtil.set(TOKEN_KEY +token, jsonObject, JwtUtils.TIME_OUT*2)) {
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
        redisUtil.del(TOKEN_KEY +oldToken);
        if (!redisUtil.set(TOKEN_KEY +newToken, newInfo, JwtUtils.TIME_OUT*2)) {
            throw new RuntimeException("缓存服务写入异常");
        }
        return newToken;
    }

    /**
     * 删除token
     * @param token
     */
    public void deleteToken(String token){
        redisUtil.del(TOKEN_KEY +token);
    }

    /**
     * 解析并验证token
     * @param token
     * @return
     */
    public JSONObject decodeVerifyToken(String token){
        JSONObject info=(JSONObject) redisUtil.get(TOKEN_KEY +token);
        if(info!=null){
            JwtUtils.verifyWithTime(token);
            return info;
        }
        throw new RuntimeException(ResponseEnum.NO_LOGIN.getMessage());
    }



}

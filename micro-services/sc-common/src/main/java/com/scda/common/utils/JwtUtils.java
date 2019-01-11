package com.scda.common.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: liuqi
 * @Date: 2019/1/10 20:41
 * @Description: jwt工具类, 依赖spring-security-jwt
 * 会话流程
 * 1.用户客户端登录成功，后台服务签发token
 * 2.客户端收到token,用Base64进行解码jwt token的.第一部分，记录validTime时间戳
 * 3.客户端发送请求前判断validTime时间戳跟当前时间比较，如果相差在2小时内，通过传入原token获取新token,再用新token获取发送请求
 * 4.（可选）如果需要对废弃的token进行回收，可以服务端可以将token放到redis里面，有效期设置为60*60*2，每次刷新token，删除redis对应的token,会话判断的时候先判断redis对应的token是否存在
 */
@Slf4j
public class JwtUtils {
    /**
     * 密钥
     */
    private static String secretKey = "scdascdascdascdascdascdascdascdascdascda";

    //token有效期(分钟) ，默认值4小时
    private final static int VALID_TIME = 60 * 60 * 4;

    private static MacSigner signer;

    static {
        signer = new MacSigner(secretKey);
    }

    /**
     * 加密token
     *
     * @param json 要加密的json
     * @return
     */
    public static String token(JSONObject json) {
        Jwt jwt = JwtHelper.encode(json.toJSONString(), signer);
        return jwt.getEncoded();
    }

    /**
     * 含有日期的凭证
     *
     * @param json
     * @return
     */
    public static String tokenWithTime(JSONObject json) {
        Map<String, String> map = new HashMap<>();
        map.put("validTime", String.valueOf(DateUtil.offsetSecond(new Date(), VALID_TIME).getTime()));
        Jwt jwt = JwtHelper.encode(json.toJSONString(), signer, map);
        return jwt.getEncoded();
    }

    /**
     * @param json    要加密的json
     * @param headers
     * @return
     */
    public static String token(JSONObject json, Map<String, String> headers) {
        Jwt jwt = JwtHelper.encode(json.toJSONString(), signer, headers);
        return jwt.getEncoded();
    }

    /**
     * 通过凭证获取新凭证
     *
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        return tokenWithTime(verifyWithTime(token));
    }

    /**
     * 带日期的凭证校验
     *
     * @param token
     * @return
     */
    public static JSONObject verifyWithTime(String token) {

        Jwt jwt = JwtHelper.decodeAndVerify(token, signer);
        Map<String, String> headerMap = JwtHelper.headers(token);
        if (headerMap.containsKey("validTime") && System.currentTimeMillis() <= Long.parseLong(headerMap.get("validTime"))) {
            return JSONObject.parseObject(jwt.getClaims());
        }
        throw new RuntimeException("过期的凭证");
    }

    /**
     * 解密token
     *
     * @return
     */
    public static JSONObject verify(String token) {
        try {
            Jwt jwt = JwtHelper.decodeAndVerify(token, signer);
            return JSONObject.parseObject(jwt.getClaims());
        } catch (RuntimeException e) {
            throw new RuntimeException("jwt签名校验失败");
        }
    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("frame", "scda");
        log.debug("数据加密前：{}", json.toJSONString());
        String token = JwtUtils.tokenWithTime(json);
        log.debug("数据加密后：{}", token);

//        Map<String,String> headerMap=new HashMap<>();
//        headerMap.put("username","username");
//        log.debug("数据加密前：{},Header：{}", json.toJSONString(),JSONObject.toJSONString(headerMap));
//        token = JwtUtils.token(json,headerMap);
//        log.debug("数据加密后：{}", token);
//        log.debug("数据解密Header：{}", JSONObject.toJSONString(JwtHelper.headers(token)));

        JSONObject info = JwtUtils.verifyWithTime(token);
        log.debug("数据解密后：{}", info.toJSONString());
        log.debug("数据解密Header：{}", JSONObject.toJSONString(JwtHelper.headers(token)));

        String newToken = refreshToken(token);
        log.debug("数据刷新后的凭证：{}", newToken);
        JSONObject newInfo = JwtUtils.verifyWithTime(newToken);
        log.debug("刷新数据解密后：{}", newInfo.toJSONString());
        log.debug("刷新数据解密Header：{}", JSONObject.toJSONString(JwtHelper.headers(newToken)));
//        Date date =new Date();
//        log.debug("当前时间："+date.getTime());
//        log.debug("十秒后时间："+DateUtil.offsetSecond(date,10).getTime());
//        log.debug("十分钟后时间："+DateUtil.offsetSecond(date,60*10).getTime());
//        log.debug("一天后时间："+DateUtil.offsetSecond(date,60*60*24).getTime());
//        log.debug("30天后时间："+DateUtil.offsetSecond(date,60*60*24*30).getTime());
//        log.debug("365天后时间："+DateUtil.offsetSecond(date,60*60*24*365).getTime());
    }
}

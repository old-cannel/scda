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
 */
@Slf4j
public class JwtUtils {
    /**
     * 密钥
     */
    private static String secretKey = "scdascdascdascdascdascdascdascdascdascda";

    //token最短有效期（默认值2小时），最长有效期是最短有效期的2倍
    public static int TIME_OUT = 60 * 60 * 2;

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
        map.put("validTime", String.valueOf(DateUtil.offsetSecond(new Date(), TIME_OUT*2).getTime()));
        map.put("timeOut",String.valueOf(DateUtil.offsetSecond(new Date(), TIME_OUT).getTime()));
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

/*        Map<String,String> headerMap=new HashMap<>();
        headerMap.put("username","username");
        log.debug("数据加密前：{},Header：{}", json.toJSONString(),JSONObject.toJSONString(headerMap));
        token = JwtUtils.token(json,headerMap);
        log.debug("数据加密后：{}", token);
        log.debug("数据解密Header：{}", JSONObject.toJSONString(JwtHelper.headers(token)));*/

//        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsInZhbGlkVGltZSI6IjE1NDc1NjYwNDg5MzkifQ.eyJhY2NvdW50Tm9uRXhwaXJlZCI6dHJ1ZSwiYWNjb3VudE5vbkxvY2tlZCI6dHJ1ZSwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6InVzZXIifV0sImNyZWRlbnRpYWxzTm9uRXhwaXJlZCI6dHJ1ZSwiZW5hYmxlZCI6dHJ1ZSwidXNlcm5hbWUiOiIxMiJ9.nlKte2BZDkD2S9cRA_MGz4O9f2ct0ZepAw5b_YuU40E";
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

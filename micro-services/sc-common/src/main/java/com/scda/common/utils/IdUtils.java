package com.scda.common.utils;

import java.util.UUID;


/**
 * @Auther: liuqi
 * @Date: 2018/9/4 09:21
 * @Description: ID生成工具
 */
public class IdUtils {
    private IdUtils() {
    }
    /**
     * 获取当前UUID
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = (uuid.toString()).replaceAll("-", "");
        return uuidStr;
    }
}

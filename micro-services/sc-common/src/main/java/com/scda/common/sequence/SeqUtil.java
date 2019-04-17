/*
 * @(#) SeqDao.java 2015年11月19日
 *
 * Copyright (c) 2015, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package com.scda.common.sequence;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;


/**
 * 获取序列号
 *
 * @author
 * @version 1.0
 * @since
 */
public class SeqUtil {


    private static Snowflake snowflake = IdUtil.createSnowflake(RandomUtil.randomInt(0, 31), RandomUtil.randomInt(0, 31));

    /**
     * 获得序列号
     */
    public static String getSeqNO(final SeqType seqType) {
        String seqNo = seqType.getPrefix() + snowflake.nextId();
        return seqNo;
    }

    /**
     * 获得任务编号
     */
    public static String getSeqNO(String taskDate) {
        String seqNo = taskDate + snowflake.nextId();
        return seqNo;
    }
}

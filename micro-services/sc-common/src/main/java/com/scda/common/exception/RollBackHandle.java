package com.scda.common.exception;

/**
 * @Auther: liuqi
 * @Date: 2019/4/16 10:01
 * @Description: 业务回滚封装
 */
public  class RollBackHandle {
    public static void rollBack(String errMsg){
        throw new BusinessException(errMsg);

    }
}

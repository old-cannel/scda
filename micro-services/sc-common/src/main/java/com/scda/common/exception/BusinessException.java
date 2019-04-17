package com.scda.common.exception;

/**
 * @Auther: liuqi
 * @Date: 2019/4/16 09:40
 * @Description: 统一业务异常抛出类
 */
public class BusinessException extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BusinessException(String message) {
        super(message);
    }
    
}

package com.scda.common.exception;

import com.scda.common.response.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: liuqi
 * @Date: 2019/1/9 15:41
 * @Description: 自定义统一的业务异常处理类
 */
@RestControllerAdvice
public class BusinessExceptionHandler {
    /**
     * 异常信息统一格式化
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseVo handleException(Exception e) {
        return ResponseVo.fail(e.getMessage());
    }

}

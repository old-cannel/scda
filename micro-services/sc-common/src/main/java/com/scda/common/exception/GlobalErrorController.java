package com.scda.common.exception;

import com.scda.common.response.ResponseVo;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: liuqi
 * @Date: 2019/1/16 09:31
 * @Description: 全局的错误处理
 */
@RestController
public class GlobalErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseVo handleError(HttpServletRequest request) {
//        //错误状态码
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            case 401:
                return ResponseVo.accessDenied(statusCode);
            case 403:
                return ResponseVo.accessDenied(statusCode);
            case 404:
                return ResponseVo.fail("请求资源不存在");
            default:
                //错误消息
                Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
                if (e == null) {
                    return ResponseVo.fail(statusCode);
                } else {
                    return ResponseVo.fail(e.getMessage());
                }
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

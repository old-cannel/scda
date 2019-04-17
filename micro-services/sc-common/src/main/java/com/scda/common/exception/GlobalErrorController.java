package com.scda.common.exception;

import com.scda.common.response.ResponseEnum;
import com.scda.common.response.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@RestController
public class GlobalErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseVo handleError(HttpServletRequest request, HttpServletResponse response) {
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
                    if (StringUtils.isNotBlank(e.getMessage())) {
                        setResponseStatus(response,e.getMessage());
                        return new ResponseVo(ResponseEnum.findCode(e.getMessage()), e.getMessage(), e.getMessage());
                    }
                    return ResponseVo.fail(e.getMessage());
                }
        }
    }

    /**
     * 根据业务码纠正对应的响应码
     * @param response 响应
     * @param errMsg 错误消息
     */
    private void setResponseStatus(HttpServletResponse response,String errMsg) {
        if (ResponseEnum.ACCESS_DENIED.getCode() == ResponseEnum.findCode(errMsg)) {
            response.setStatus(403);
        }
        if (ResponseEnum.NO_LOGIN.getCode() == ResponseEnum.findCode(errMsg)) {
            response.setStatus(401);
        } else {
            response.setStatus(200);
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

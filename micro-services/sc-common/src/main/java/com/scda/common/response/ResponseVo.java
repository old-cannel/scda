package com.scda.common.response;

/**
 * @author liuqi
 * @Date 2018-04-27.
 * @describe 响应
 */
public class ResponseVo {

    /* 编码：代码 */
    private int code;
    /* 错误信息：对应于code */
    private String msg;

    /* 返回结果 */
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    private ResponseVo() {
    }

    /**
     * 自定义响应
     *
     * @param code   　响应状态码
     * @param msg    　错误原因
     * @param result 　业务结果
     */
    public ResponseVo(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    /**
     * 预定义响应，如果当前枚举没有相应消息，可自行在枚举后面追加codo和msg
     *
     * @param responseEnum 响应码，错误信息
     * @param result       成功业务结果
     */
    public static ResponseVo of(ResponseEnum responseEnum, Object result) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(responseEnum.getCode());
        responseVo.setMsg(responseEnum.getMessage());
        if (result != null) {
            responseVo.setResult(result);
        }

        return responseVo;
    }

    /**
     * 请求成功
     *
     * @param result
     * @return
     */
    public static ResponseVo success(Object result) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.SUCCESS.getCode());
        responseVo.setMsg(ResponseEnum.SUCCESS.getMessage());
        if (result != null) {
            responseVo.setResult(result);
        }

        return responseVo;
    }

    /**
     * 请求失败
     *
     * @param result
     * @return
     */
    public static ResponseVo fail(Object result) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.FAIL.getCode());
        responseVo.setMsg(ResponseEnum.FAIL.getMessage());
        if (result != null) {
            responseVo.setResult(result);
        }

        return responseVo;
    }

    /**
     * 请求登录
     *
     * @return
     */
    public static ResponseVo noLogin() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.NO_LOGIN.getCode());
        responseVo.setMsg(ResponseEnum.NO_LOGIN.getMessage());
        return responseVo;
    }

    /**
     * 没有数据
     *
     * @return
     */
    public static ResponseVo noData() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.NO_DATA.getCode());
        responseVo.setMsg(ResponseEnum.NO_DATA.getMessage());
        return responseVo;
    }

    /**
     * 业务数据验证未通过
     *
     * @param result
     * @return
     */
    public static ResponseVo validFail(Object result) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.VALID_FAIL.getCode());
        responseVo.setMsg(ResponseEnum.VALID_FAIL.getMessage());
        if (result != null) {
            responseVo.setResult(result);
        }

        return responseVo;
    }

    /**
     * 拒绝访问，没有权限
     *
     * @param result
     * @return
     */
    public static ResponseVo accessDenied(Object result) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.ACCESS_DENIED.getCode());
        responseVo.setMsg(ResponseEnum.ACCESS_DENIED.getMessage());
        if (result != null) {
            responseVo.setResult(result);
        }

        return responseVo;
    }

    /**
     * 系统繁忙，请稍后再试
     *
     * @param result
     * @return
     */
    public static ResponseVo busy(Object result) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.SYSTEM_BUSY.getCode());
        responseVo.setMsg(ResponseEnum.SYSTEM_BUSY.getMessage());
        if (result != null) {
            responseVo.setResult(result);
        }

        return responseVo;
    }

    @Override
    public String toString() {
        return "ResponseVo{" + "code=" + code + ", result=" + result + ", msg='" + msg + '\'' + '}';
    }
}

package com.scda.common.response;

import java.io.Serializable;

/**
 * @author liuqi
 * @Date 2018-04-27.
 * @describe 响应
 */
public class ResponseVo implements Serializable {

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

  public void setResult(Serializable result) {
    this.result = result;
  }

  private ResponseVo() {
  }

  /**
   * 自定义响应
   * @param code　响应状态码
   * @param msg　错误原因
   * @param result　业务结果
   */
  public ResponseVo(int code, String msg, Object result) {
    this.code = code;
    this.msg = msg;
    this.result = result;
  }

  /**
   * 预定义响应，如果当前枚举没有相应消息，可自行在枚举后面追加codo和msg
   * @param responseEnum 响应码，错误信息
   * @param result 成功业务结果
   */
  public static ResponseVo of(ResponseEnum responseEnum,Object result) {
    ResponseVo responseVo=new ResponseVo();
    responseVo.setCode(responseEnum.getCode());
    responseVo.setMsg(responseEnum.getMessage());
    if(result!=null){
      responseVo.setResult((Serializable) result);
    }

    return  responseVo;
  }

}

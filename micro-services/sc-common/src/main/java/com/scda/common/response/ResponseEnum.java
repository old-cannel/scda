package com.scda.common.response;

/**
 * @author liuqi
 * @Date 2018-04-27.
 * @describe 预定义响应
 */
public enum ResponseEnum {
  /* 默认成功信息 */
  SUCCESS(10000, "处理成功"),
  /* 默认失败信息 */
  FAIL(20000, "请求失败"),
  NO_DATA(20001, "暂无数据！"),
  NO_LOGIN(10001, "请先登录"),

  ;
  private int code;
  private String message;

  public int getCode() {
    return code;
  }
  public String getMessage() {
    return message;
  }

  ResponseEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

}

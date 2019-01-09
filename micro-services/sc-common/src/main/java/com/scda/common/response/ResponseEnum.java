package com.scda.common.response;

/**
 * @author liuqi
 * @Date 2018-04-27.
 * @describe 预定义响应
 */
public enum ResponseEnum {
  /* 成功信息 */
  SUCCESS(10000, "处理成功"),
  /**未登录 无数据**/
  NO_LOGIN(10001, "请先登录"),
  NO_DATA(10002, "暂无数据！"),
  /* 失败信息 */
  FAIL(20000, "请求失败"),
  VALID_FAIL(20002, "参数格式不正确"),

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

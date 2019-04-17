package com.scda.common.sequence;

/**
 * 编码类型
 * （生成编码的默认格式：前缀名+固定长度序号）
 *
 * @author
 * @version
 * @since
 */
public enum SeqType {



    /**
     * 用户编码
     */
    USER_CODE("U"),
   ;





    /**
     * 前缀名
     */
    private String prefix;

    SeqType(String prefix) {
        this.prefix = prefix;
    }





    public String getPrefix() {
        return prefix;
    }


}

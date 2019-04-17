/*
 * Copyright (c) 2018, SIMPO Technology. All Rights Reserved. SIMPO Technology. CONFIDENTIAL
 */
package com.scda.vo;

/**
 * base64文件
 *
 * @author lilanfei
 * @version 1.0
 * @since 2018年06月25日
 */
public class Base64File {

    private String originalName;

    private String base64string;

    private long size;

    private String fileType;

    /**
     * 获取originalName
     *
     * @return originalName
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * 设置originalName
     *
     * @param originalName originalName
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * 获取base64string
     *
     * @return base64string
     */
    public String getBase64string() {
        return base64string;
    }

    /**
     * 设置base64string
     *
     * @param base64string base64string
     */
    public void setBase64string(String base64string) {
        this.base64string = base64string;
    }

    /**
     * 获取size
     *
     * @return size
     */
    public long getSize() {
        return size;
    }

    /**
     * 设置size
     *
     * @param size size
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 获取fileType
     *
     * @return fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置fileType
     *
     * @param fileType fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}

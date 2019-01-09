package com.scda.common.db.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by liuqi on 2017-05-12.
 */
@Data
public class BaseFieldVo implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8820913334214200448L;
    /* 主键 */

    private static final int DEFAULT_PAGE_SIZE = 10;
    /* 主键 */
    private String id;
    private String delFlag;//逻辑删除标识

    private Date addTime;//新增时间
    private String addUserCode;//新增用户id
    private String addMark;//新增备注

    private Date updTime;//更新时间
    private String updUserCode;//更新用户id
    private String updMark;//更新备注

}

package com.scda.security.vo;

import com.scda.common.db.entity.BaseFieldVo;
import com.scda.common.db.util.BaseFieldValueUtils;
import com.scda.security.util.MySecurityContextHolder;

/**
 * @Auther: liuqi
 * @Date: 2019/4/17 10:33
 * @Description: 共同处理
 */
public class EntityUtil {
    /**
     * 插入数据时，设置DB表的共通字段值
     *
     * @param vo 更新条件
     * @param pageId 插入页面Id
     */
    public static void beforInsert(BaseFieldVo vo, String pageId) {
        BaseFieldValueUtils.setCommonField4Insert(vo, pageId, MySecurityContextHolder.getUserCode());
    }

    /**
     * 更新数据时，设置DB表的共通字段值
     *
     * @param vo 更新条件
     * @param pageId 更新页面Id
     */
    public static void beforUpdate(BaseFieldVo vo, String pageId) {
       BaseFieldValueUtils.setCommonField4Update(vo,pageId,MySecurityContextHolder.getUserCode());
    }
}

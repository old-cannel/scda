package com.scda.common.db.util;


import com.scda.common.db.entity.BaseFieldVo;
import com.scda.common.utils.IdUtils;

import java.util.Date;
import java.util.UUID;


/**
 * 默认字段赋值工具类
 * Created by liuqi on 2017-05-12.
 */
public class BaseFieldValueUtils {

	/**
	 * 插入数据时，设置DB表的共通字段值
	 *
	 * @param vo 更新条件
	 * @param pageId 插入页面Id
	 * @param userCode 插入者
	 */
	public static void setCommonField4Insert(BaseFieldVo vo, String pageId, String userCode) {
		if (vo == null) {
			return;
		}

		vo.setId(IdUtils.getUUID());
		Date now = new Date();
		vo.setDelFlag("0");
		vo.setAddMark(pageId.length()>9?pageId.substring(0,9):pageId);
		vo.setAddTime(now);
		vo.setAddUserCode(userCode);
		vo.setUpdMark(pageId.length()>9?pageId.substring(0,9):pageId);
		vo.setUpdTime(now);
		vo.setUpdUserCode(userCode);
	}

	/**
	 * 更新数据时，设置DB表的共通字段值
	 *
	 * @param vo 更新条件
	 * @param pageId 更新页面Id
	 * @param userCode 更新者
	 */
	public static void setCommonField4Update(BaseFieldVo vo, String pageId, String userCode) {
		if (vo == null) {
			return;
		}
		Date now = new Date();
		vo.setUpdMark(pageId.length()>9?pageId.substring(0,9):pageId);
		vo.setUpdTime(now);
		vo.setUpdUserCode(userCode);
	}

}

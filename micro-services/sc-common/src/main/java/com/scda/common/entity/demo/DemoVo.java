package com.scda.common.entity.demo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: liuqi
 * @Date: 2018/11/13 14:49
 * @Description:
 */
@TableName("demo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoVo extends BaseFieldVo<DemoVo> {
    private String code;
    private String name;
}

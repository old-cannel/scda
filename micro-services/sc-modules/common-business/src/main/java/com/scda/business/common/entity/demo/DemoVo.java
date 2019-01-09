package com.scda.business.common.entity.demo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import com.scda.common.valid.IsNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Auther: liuqi
 * @Date: 2018/11/13 14:49
 * @Description:
 */
@TableName("demo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoVo extends BaseFieldVo {
    @NotBlank(message = "编码不能为空")
    @IsNumber(message = "编码必须为数字")
    private String code;
    @Length(min = 3,message = "名称长度不能小于3")
    private String name;
}

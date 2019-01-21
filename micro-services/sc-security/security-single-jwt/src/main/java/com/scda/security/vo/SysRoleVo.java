package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 15:58
 * @Description: 角色
 */
@TableName("sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleVo extends BaseFieldVo {
    //编码
    private String code;

    @NotBlank(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称长度不能大于30")
    private String name;

    @Length(max = 256, message = "备注长度不能大于256")
    private String remark;

}

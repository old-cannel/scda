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
 * @Description: 组织机构
 */
@TableName("sys_organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysOrganizationVo extends BaseFieldVo {
    //编码
    private String code;

    @NotBlank(message = "组织名称不能为空")
    @Length(max = 30, message = "组织名称长度不能大于30")
    private String name;

    @Length(max = 256, message = "备注长度不能大于256")
    private String remark;

    //机构类型，0公司，1部门，2组，3其他
    @NotBlank(message = "机构类型不能为空")
    private String orgType;

    //上级编码
    private String supCode;
    //上级名称
    private String supName;
    //上级编码集合
    private String supCodes;
    //上级名称集合
    private String supNames;


}

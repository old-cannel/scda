package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 用户
 */
@TableName("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVo extends BaseFieldVo {
    //编码
    private String code;

    @NotBlank(message = "用户名不能为空")
    @Length(max = 50, message = "用户名长度不能大于50")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 100, message = "密码长度最低8，最大100")
    private String password;

    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 100, message = "密码长度最低8，最大100")
    @TableField(exist = false)
    private String repeatPassword;

    @Length(max = 20, message = "联系方式长度不能大于20")
    private String contact;

    @Length(max = 20, message = "工号长度不能大于20")
    private String workNum;

    @Length(max = 20, message = "姓名长度不能大于20")
    private String fullName;

    @Length(max = 250, message = "备注长度不能大于250")
    private String remark;

    //所属机构编码
    private String srcOrgCode;
    //没有过期
    private int nonExpired;
    //启用
    private int enabled;
    //没有锁定
    private int nonLocked;


}

package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 15:58
 * @Description: 用户
 */
@TableName("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVo extends UserBaseVo implements UserDetails {


    @Length(max = 20, message = "工号长度不能大于20")
    private String workNum;

    @Length(max = 20, message = "姓名长度不能大于20")
    private String fullName;

    @Length(max = 18, message = "身份证长度不能大于18")
    private String idNum;


    @Length(max = 20, message = "电话号长度不能大于20")
    private String phoneCall;


    @Length(max = 20, message = "手机号长度不能大于20")
    private String mobileNum;

    @Length(max = 30, message = "邮箱长度不能大于30")
    private String email;

    //所属机构编码
    private String srcOrgCode;

    @Length(max = 250, message = "备注长度不能大于250")
    private String remark;

    @Length(max = 20, message = "岗位长度不能大于20")
    private String position;

    //入职时间
    private Date entryTime;


}

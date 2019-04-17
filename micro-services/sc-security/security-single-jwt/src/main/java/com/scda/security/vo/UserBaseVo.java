package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 15:58
 * @Description: 用户
 */
@TableName("user_base")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseVo extends BaseFieldVo implements UserDetails {
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

    @NotBlank(message = "是否超级管理员不能为空")
    //超级管理员标识
    private String adminFlag;


    //没有过期
    private boolean nonExpired;
    //启用
    private boolean enabled;
    //没有锁定
    private boolean nonLocked;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isNonExpired() ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNonLocked() ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}

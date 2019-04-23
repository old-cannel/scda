package com.scda.security.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.scda.common.db.entity.BaseFieldVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: liuqi
 * @Date: 2019/1/17 15:58
 * @Description: 操作接口关系
 */
@TableName("sys_operation_api")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysOperationApiVo extends BaseFieldVo {
    //编码
    private String code;

    @NotBlank(message = "接口不能为空")
    private String apiId;

    //菜单id
    @NotBlank(message = "操作不能为空")
    private String operationId;

}

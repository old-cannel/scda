package com.scda.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: liuqi
 * @Date: 2019/1/9 15:16
 * @Description: 自定义格式校验-必须是数字
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNumberValidator.class)
public @interface IsNumber {
    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

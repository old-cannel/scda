package com.scda.common.db.dyndata;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author liuqi
 * @Date 2018-06-08.
 * @describe 动态数据源切面拦截更换
 */
//@Aspect
//@Component
//@Order(1)
public class DynDataSourceAspect {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Pointcut("execution( * com.scda..*ServiceImpl.*(..))")
  public void changDb(){
  }
  @Before("changDb()")
  public void doBefore(JoinPoint joinPoint) {
    logger.info("动态数据源--》切换开始");
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();
    //      获取动态数据源注解
    boolean methodAnnotation = method.isAnnotationPresent(TargetDataSource.class);
    TargetDataSource targetDataSource = null;
    if (methodAnnotation) {
//注解存在
      targetDataSource = method.getAnnotation(TargetDataSource.class);
    } else {
//      注解不存在，获取接口注解
      Class clazz[] = joinPoint.getTarget().getClass().getInterfaces();
      targetDataSource = (TargetDataSource) clazz[0].getAnnotation(TargetDataSource.class);
    }
    if (targetDataSource != null) {
      logger.info("动态数据源--》切换结果：{}",targetDataSource.name());
//      存在注解的数据源
      DynamicDataSourceContextHolder.putDataSource(targetDataSource.name());
    }else{
      logger.info("动态数据源--》没有找到相应注解，采用默认数据源。切换结果：{}", DynDataSourceRegister.ONE);
//      不存在，采用默认
      DynamicDataSourceContextHolder.putDataSource(DynDataSourceRegister.ONE);
    }
    logger.info("动态数据源--》切换结束。");
  }

  }

package com.ling.datasource.aop;

import com.ling.datasource.annotation.DataSource;
import com.ling.datasource.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
 * 优先根据注解动态切换数据源
 * 没有注解，根据包动态切换数据源
 */
@Aspect  
@Component
@Order(-10)
public class DynamicDataSourceAspect {  
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("@annotation(com.ling.datasource.annotation.DataSource)" +
            " || execution(public * com..service.*.*(..))" +
            " || execution(public * cn..service.*.*(..))" +
            " || execution(public * org..service.*.*(..))" +
            " || execution(public * net..service.*.*(..))")
    public void useDataSource() {
    }
  
    @Before("useDataSource()")  
    public void changeDataSource(JoinPoint point) throws Throwable {
        String sourceId = "";
        //获取目标对象的类类型
        Class<?> aClass = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        if (annotation != null){
            sourceId = annotation.name();
        }else {
            //获取包名用于区分不同数据源
            String whichDataSource = aClass.getName();
            for (String dataSourceId : DynamicDataSourceContextHolder.dataSourceIds) {
                if (whichDataSource.contains(dataSourceId)) {
                    sourceId = dataSourceId;
                }
            }
        }
        
        if (!DynamicDataSourceContextHolder.containsDataSource(sourceId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 >{}",
                    sourceId,
                    point.getSignature());
        } else {
            logger.info("Use DataSource : {}>{}",
                    sourceId,
                    point.getSignature());
            //如果容器中有数据源，那么就把数据源标识设置到ThreadLocal中
            DynamicDataSourceContextHolder.setDataSourceType(sourceId);
        }
    }  
  
    @After("useDataSource()")  
    public void restoreDataSource(JoinPoint point) {  
        DynamicDataSourceContextHolder.clearDataSourceType();
    }  
}  
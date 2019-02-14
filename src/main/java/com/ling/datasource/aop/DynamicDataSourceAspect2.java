package com.ling.datasource.aop;

import com.ling.datasource.annotation.DataSource;
import com.ling.datasource.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 纯注解动态切换数据源
 */
// @Aspect  
// @Component
// @Order(-10)
public class DynamicDataSourceAspect2 {  
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect2.class);  
  
    @Before("@annotation(ds)")  
    public void changeDataSource(JoinPoint point, DataSource ds) throws Throwable {  
        String dsId = ds.name();  
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 >{}",
                    ds.name(),
                    point.getSignature());
        } else { 
            logger.info("Use DataSource : {}>{}" , 
                    ds.name(),
                    point.getSignature());
            //如果容器中有数据源，那么就把数据源标识设置到ThreadLocal中
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());  
        }       
    }  
  
    @After("@annotation(ds)")  
    public void restoreDataSource(JoinPoint point, DataSource ds) {  
        System.err.println("Revert DataSource : "+ds.name()+" > "+point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
              
    }  
}  
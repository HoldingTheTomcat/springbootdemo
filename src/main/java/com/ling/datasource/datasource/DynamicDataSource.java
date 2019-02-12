package com.ling.datasource.datasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 即动态数据源切换类：
 * 1）如果要用到spring中的动态数据切换功能，那么必须要继承AbstractRoutingDataSource
 * 2）然后让这个类实例化，并且交给spring管理
 * 3）类交给spring管理的方法
 * 	 1、在类上加spring注解
 *	 2、创建beanDefinition对象，然后设置BeanClass属性，这样这个BeanClass就会被spring实例化，并且交给spring管理
 */
public class DynamicDataSource extends AbstractRoutingDataSource {  
	
	 private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 该方法必须被重写
	 * 其实就是为了找到方法上的注解配置的数据源
	 * 即：获取当前方法对应的数据源标识，告诉spring当前的LookupKey，告诉spring具体操作的数据源是哪一个
	 */
	
	@Override  
    protected Object determineCurrentLookupKey() {  
    	  if(DynamicDataSourceContextHolder.getDataSourceType()==null){
			  logger.debug("数据源为:====master");
    	  }else{
    	  	  logger.debug("数据源为:===={}", DynamicDataSourceContextHolder.getDataSourceType());
    	  }
    	  
          return DynamicDataSourceContextHolder.getDataSourceType();  
    }  
  
}  
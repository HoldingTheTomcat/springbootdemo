package com.ling.datasource.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前线程数据源：建立映射关系，建立当前用户操作线程和数据源标识的映射
 */
  
public class DynamicDataSourceContextHolder {  
     private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
 
	 /*
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     * ThreadLocal  :即它是一个map，只能存一个，key是它的线程id
     * set(){
     *      map.put(Thread.currentThread(),String)
     * }
     */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(); 
	
	//所有的数据源id容器
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 在切面方法执行之前，即切面Before，会把数据源id（可以是注解获得、可以根据包获得），放入到ThreadLocal中
     * 数据源类型跟当前线程绑定：设置数据源名
     */
    
    public static void setDataSourceType(String dataSourceType) {
        logger.info("切换到{}数据源", dataSourceType);
        contextHolder.set(dataSourceType);  
    }  
    
    //获取数据源名
    public static String getDataSourceType() {  
        return contextHolder.get();  
    }

    /**
     * 在切面方法执行之后，即切面after，会清空当前ThreadLocal，目的是保证下个请求到来前，ThreadLocal中放入新的数据源id
     * 清除数据源名
     */
    
    public static void clearDataSourceType() {  
        contextHolder.remove();  
    }  
  
    /** 
     * 判断指定DataSrouce在容器内是否存在
     *      ：目的是判断你写注解数据源id、根据包获得的数据源id,在数据源容器中是否有，一般没有就采用默认数据源 
     * @param dataSourceId 
     * @return   
     */  
    public static boolean containsDataSource(String dataSourceId) {  
        return dataSourceIds.contains(dataSourceId);  
    }  
}  
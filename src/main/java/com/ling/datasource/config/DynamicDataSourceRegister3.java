package com.ling.datasource.config;

import com.ling.datasource.datasource.DynamicDataSource;
import com.ling.datasource.datasource.DynamicDataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.validation.DataBinder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责把数据源注册到springBoot里面
 * 功能描述：动态数据源注册 启动动态数据源请在启动类中（如Start）
 * 添加 @Import(DynamicDataSourceRegister3.class)
 * 在这个类被加载的时候做一些操作，如何保证这个类被加载的时候做一些操作呢，需要实现接口ImportBeanDefinitionRegistrar
 */
/*
@EnableTransactionManagement
@EnableConfigurationProperties(DataSourceProperties.class)
@MapperScan("com.appleyk")  
这里先不加
*/
public class DynamicDataSourceRegister3 implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister3.class);

	//数据源转换器
	private ConversionService conversionService = new DefaultConversionService();
	//spring IOC 属性相关
	private PropertyValues dataSourcePropertyValues;

	// 如配置文件中未指定数据源类型，使用该默认值
	private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

	// 默认数据源
	private DataSource defaultDataSource;
	
	//自定的多个其它数据源
	private Map<String, DataSource> customDataSources = new HashMap<>();

	private String  defaultKey;

	/**
	 * 把数据库连接池初始化、实例化
	 * @param importingClassMetadata
	 * @param registry
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		//设置目标数据源
		Map<Object, Object> targetDataSources = new HashMap<>();
		// 将主数据源添加到更多数据源中
		targetDataSources.put(defaultKey, defaultDataSource);
		DynamicDataSourceContextHolder.dataSourceIds.add(defaultKey);

		// 添加更多数据源
		targetDataSources.putAll(customDataSources);
		for (String key : customDataSources.keySet()) {
			DynamicDataSourceContextHolder.dataSourceIds.add(key);
		}

		/**
         * 创建GenericBeanDefinition用来实例化DynamicDataSource
		 * DynamicDataSource它是有属性的（父接口AbstractRoutingDataSource中），这些属性，我们也需要让它初始化
		 */
		
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		//实例化DynamicDataSource
		beanDefinition.setBeanClass(DynamicDataSource.class);
		beanDefinition.setSynthetic(true);
		
		//获得DynamicDataSource的属性
		MutablePropertyValues mpv = beanDefinition.getPropertyValues();
		//属性初始化， -- defaultTargetDataSource、targetDataSources都是接口AbstractRoutingDataSource的属性名
		mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
		mpv.addPropertyValue("targetDataSources", targetDataSources);
		
		//注册到spring中，交给管理 --实现ImportBeanDefinitionRegistrar，目的就是把beanDefinition注册到spring容器中
		registry.registerBeanDefinition("dataSource", beanDefinition);
		
		logger.info("动态数据源注册成功,从数据源个数 == {}", customDataSources.size());
	}


	/**
	 * 加载多数据源配置:即加载我们自定义的数据源配置信息
	 */
	@Override
	public void setEnvironment(Environment env) {
		defaultKey = Binder.get(env).bind("dynamic-datasource.default-key", Bindable.of(String.class)).get();
		//默认数据源解析
		initDefaultDataSource(env);
		initCustomDataSources(env);
	}

	/**
	 * 初始化主数据源
	 * 
	 */
	private void initDefaultDataSource(Environment env) {

		// 读取主数据源
		Binder binder = Binder.get(env);
		Map<String, Object> sourceMap = binder.bind("dynamic-datasource.datasources."+ defaultKey, Bindable.mapOf(String.class, Object.class)).get();
		Map<String, Object> dsMap = new HashMap<>();
		dsMap.put("type", sourceMap.get("type"));
		dsMap.put("driver-class-name", sourceMap.get("driver-class-name"));
		dsMap.put("url", sourceMap.get("url"));
		dsMap.put("username", sourceMap.get("username"));
		dsMap.put("password", sourceMap.get("password"));

		//根据连接信息，创建数据源实例DataSource
		defaultDataSource = buildDataSource(dsMap);

		dataBinder(defaultDataSource,env);

	}

	/**
	 * 根据连接信息，创建数据源实例DataSource
	 * @return DataSource
	 */
	@SuppressWarnings("unchecked")
	public DataSource buildDataSource(Map<String, Object> dsMap) {
		try {
			Object type = dsMap.get("type");

			if (type == null)
				type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource

			//反射实例化连接类型
			Class<? extends DataSource> dataSourceType;
			dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

			String driverClassName = dsMap.get("driver-class-name").toString();
			String url = dsMap.get("url").toString();
			String username = dsMap.get("username").toString();
			String password = dsMap.get("password").toString();

			//把数据源的一些信息绑定到springboot中
			DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
					.username(username).password(password).type(dataSourceType);

			return factory.build();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 为springboot绑定DataSource
	 * 把一些配置信息告诉springboot
	 * @param dataSource
	 * @param env
	 * 
	 */
	private void dataBinder(DataSource dataSource, Environment env) {
		DataBinder dataBinder = new DataBinder(dataSource);
		dataBinder.setConversionService(conversionService);
		dataBinder.setIgnoreInvalidFields(false);//false
		dataBinder.setIgnoreUnknownFields(true);//true
		if (dataSourcePropertyValues == null) {
			Map<String, Object> rpr = Binder.get(env).bind("dynamic-datasource.datasources", Bindable.mapOf(String.class, Object.class)).get();
			Map<String, Object> values = new HashMap<>(rpr);
			// 排除已经设置的属性
			values.remove("type");
			values.remove("driverClassName");
			values.remove("url");
			values.remove("username");
			values.remove("password");
			dataSourcePropertyValues = new MutablePropertyValues(values);
		}
		dataBinder.bind(dataSourcePropertyValues);
	}

	/**
	 * 初始化更多数据源：初始化其它自定义数据源
	 * 
	 */
	private void initCustomDataSources(Environment env) {
		// 读取库表中datasource获取更多数据源
		// 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
		Binder binder = Binder.get(env);
		Map<String, Object> keyMap = binder.bind("dynamic-datasource.datasources", Bindable.mapOf(String.class, Object.class)).get();
		keyMap.remove(defaultKey);
		// 自定义的其它数据源
		for (String dsPrefix : keyMap.keySet()) {
			Map<String, Object> dsMap = binder.bind("dynamic-datasource.datasources." + dsPrefix, Bindable.mapOf(String.class, Object.class)).get();
			DataSource ds = buildDataSource(dsMap);
			customDataSources.put(dsPrefix, ds);
			dataBinder(ds, env);
			try {
				// 判断一下 数据源是否连接成功
				ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	// @Bean
	// public PlatformTransactionManager masterTransactionManager() {
	// 	logger.info("masterTransactionManager=========配置主数据库的事务");
	// 	return new DataSourceTransactionManager(defaultDataSource);
	// }
    //
	// @Bean
	// public PlatformTransactionManager slaveTransactionManager() {
	// 	logger.info("slaveTransactionManager=========配置从数据库的事务");
	// 	return new DataSourceTransactionManager(customDataSources.get("slave"));
	// }

}

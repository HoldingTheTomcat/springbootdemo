package com.ling.config;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/***
 * 配置test数据源
 * @author TianHeling
 * @date 2019/2/9 9:01  
 * @return 
 */
@Configuration
@MapperScan(basePackages = "com.ling.dao.mapper.test", sqlSessionTemplateRef = "testSqlSessionTemplate")
public class DataSourceTest {

    @Autowired
    private Environment env;
    
    /**
     * 配置test数据库的连接信息
     * @return
     */
    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "testSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //这里可以手动指定mapper.xml文件的位置，或者读取配置文件中定的位置
        // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return bean.getObject();
    }

    /**
     * 配置事务管理
     * @param dataSource
     * @return
     */
    @Bean(name = "testTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("testDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "testSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("testSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

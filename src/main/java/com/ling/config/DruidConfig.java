package com.ling.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/19
 */
@Configuration
public class DruidConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 使用DruidDataSource连接数据库
     * 并注入连接池相关配置
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSourceConfig() {
        return new DruidDataSource();
    }
}

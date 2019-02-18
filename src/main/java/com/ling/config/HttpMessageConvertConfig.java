package com.ling.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/2/6
 */
@Configuration
public class HttpMessageConvertConfig {
    
    @Bean
    public HttpMessageConverters fastJsonMessageConvert(){
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //创建fastJson配置对象
        FastJsonConfig config = new FastJsonConfig();
        //对json数据进行格式化
        config.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat);
        converter.setFastJsonConfig(config);
        //测试单个文件提交-2 
        //测试单个文件提交-3
        return new HttpMessageConverters(converter);
    }
    
}

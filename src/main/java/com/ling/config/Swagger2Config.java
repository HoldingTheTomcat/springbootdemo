package com.ling.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/5/7
 */
@Configuration
@Profile({"dev","test"})
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("合同后台管理接口")
                .apiInfo(contractInfo())
                .select()//创建接口过滤对象
                .apis(RequestHandlerSelectors.basePackage("com.ling.controller"))//api接口包扫描路径
                // .paths(PathSelectors.regex(".*/admin/.*"))//可以根据url路径设置哪些请求加入文档，设置哪些忽略
                .paths(Predicates.or(PathSelectors.regex("/api2/.*")))
                // 忽略：not
                .paths(Predicates.not(PathSelectors.regex("/api2/setStudent/.*")))
                .build();
    }

    private ApiInfo contractInfo() {
        return new ApiInfoBuilder()
                .title("合同后台管理接口API 文档")
                .description(
                        "合同服务名：spring-boot-demo\r\n" + "请求地址：http://www.lingzi.com"
                )
                .termsOfServiceUrl("http://www.lingzi.com")
                .version("1.0.0")//版本
                .contact(new Contact("lingzi", "http://www.lingzi.com", "17778029272@163.com")) //作者
                .license("领子信息接口，version 2.0")//许可证信息
                .licenseUrl("http://www.lingzi.com/license/license-2.0.html")//许可证地址
                .build();
    }

    @Bean
    public Docket loanApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("贷款后台管理接口")
                .apiInfo(loanInfo())
                // .useDefaultResponseMessages(false) 关闭使用默认返回格式
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ling.controller.loan"))//api接口包扫描路径
                // .paths(PathSelectors.regex(".*/admin/.*"))//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 根据注解分组：可以使用自定义注解
     * 这样可以不用分包，直接定义注解
     * 类注解、方法注解同时作为配置要求时：需要两个注解都满足，才在Api中显示
     * 应该：多个注解要求：比如方法注解需要有@ApiOperation、@Loan注解时，才表示贷款分组
     */
    @Bean
    public Docket loanInterFaceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("注解分组后台管理接口")
                .apiInfo(loanInfo())
                .select()
                //api接口包扫描路径,根据类注解分组，
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//指定只有使用了@Api注解的类，才在Api中显示
                //api接口包扫描路径,根据方法注解分组，
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//或者指定只有添加了ApiOperation注解的方法,才在Api中显示
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 自定义或注解，满足条件，才在Api中显示
     * 根据或注解：分组
     */

    @Bean
    public Docket loanOrApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("或分组后台管理接口")
                .apiInfo(loanInfo())
                .select()
                //条件设置
                .apis(setApis())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 或条件设置
     *
     * @return
     */
    public Predicate<RequestHandler> setApis() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {

                Class<?> declaringClass = input.declaringClass();
                Package aPackage = declaringClass.getPackage();
                System.out.println("package" + aPackage.toString());

                if (declaringClass == BasicErrorController.class)// 排除
                    return false;
                if (declaringClass.isAnnotationPresent(Api.class)) // 被注解的类
                    return true;
                if (input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
                    return true;
                if (input.isAnnotatedWith(ApiOperation.class))//只有添加了ApiOperation注解的method才在API中显示
                    return true;
                if (aPackage.toString().equals("package com.ling.controller.ling2")) {
                    return true;
                }
                return false;
            }
        };
        return predicate;
    }

    private ApiInfo loanInfo() {
        return new ApiInfoBuilder()
                .title("注解分组管理接口API 文档")
                .description(
                        "贷款服务名：spring-boot-demo\r\n" + "请求地址：http://www.lingzi.com"
                )
                .termsOfServiceUrl("http://www.lingzi.com")
                .version("1.0.0")//版本
                .contact(new Contact("lingzi", "http://www.lingzi.com", "17778029272@163.com")) //作者
                .license("领子信息接口，version 2.0")//许可证信息
                .licenseUrl("http://www.lingzi.com/license/license-2.0.html")//许可证地址
                .build();
    }


}
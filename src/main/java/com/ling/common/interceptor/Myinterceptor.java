package com.ling.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LingZi on 2018/11/21.
 */
@Configuration
public class Myinterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        HandlerInterceptor inter = new HandlerInterceptor() {

            // controller执行后且视图返回后调用此方法
            // 这里可得到执行controller时的异常信息
            // 这里可记录操作日志
            @Override
            public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
                // 设置为true，测试使用
                return true;
            }

            // controller执行后但未返回视图前调用此方法
            // 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
            @Override
            public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

            }

            // Controller执行前调用此方法
            // 返回true表示继续执行，返回false中止执行
            // 这里可以加入登录校验、权限拦截等
            @Override
            public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

            }
        };
        // registry.addInterceptor(inter).addPathPatterns("/**").excludePathPatterns("排除拦截的");
        //拦截所有
        registry.addInterceptor(inter).addPathPatterns("/**");
    }
}

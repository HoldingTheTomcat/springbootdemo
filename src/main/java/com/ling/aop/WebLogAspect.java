package com.ling.aop;

import com.alibaba.fastjson.JSON;
import com.ling.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/1
 */

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //定义一个切点，格式就是按切点对应的匹配格式
    // @Pointcut("execution(* *.saying(..))")
    @Pointcut("execution(* com.ling.controller..*.*(..))")
    public void controllerPoint() {
    }//controllerPoint就是切点名，名字任意 

    @Pointcut("execution(* com.ling.service.serviceImpl..*.*(..))")
    public void servicePoint() {
    }//

    /**
     * 环绕通知。如果原方法异常了，那么prroceed后面的不执行，注意要有ProceedingJoinPoint参数传入。ProceedingJoinPoint只能用于环绕通知，其它类型通知不能使用
     * 还可以加上调用者，即登录人
     */
    @Around("controllerPoint()")
    public Object controllerPointAround(ProceedingJoinPoint point) throws Throwable {
        // logger.info("环绕通知进入方法");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // String ip = request.getRemoteAddr();
        String ip = IpUtils.getClientIP(request);
        String className = getRealClassName(point);
        // String method = request.getMethod();
        String methodName = getMethodName(point);
        Object[] args = point.getArgs();
        String url = request.getRequestURI();

        //从切面织入点处通过反射机制获取织入点处的方法（被拦截的方法）
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        StringBuilder param = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            param.append(parameters[i].getName() + ":" + JSON.toJSONString(args[i]));
            if (i < parameters.length - 1) {
                param.append(",");
            }

        }
        Class<?> returnType = method.getReturnType();
        logger.info("controller调用开始 ->ip:{}  ->请求地址：{} ->调用类：{} ->方法：{} ->参数：{} ->返回值类型：{}", ip, url,className, methodName, param.toString(), JSON.toJSONString(returnType));
        Object result = point.proceed();//放行原方法
        logger.info("controller调用结束 ->ip:{}  ->请求地址：{} ->调用类：{} ->方法：{}->参数：{} ->执行结果：{}", ip, url,className, methodName, param.toString(), JSON.toJSONString(result));
        // logger.info("环绕通知退出方法");
        //这里也可以做插入日志记录相关操作，如果是成功才插入，那么service可以根据业务逻辑抛出异常，那么proceed后面就不会执行
        return result;
    }

    @Around("servicePoint()")
    public Object servicePointAround(ProceedingJoinPoint point) throws Throwable{
        // logger.info("环绕通知进入方法");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String ip = IpUtils.getClientIP(request);
        String className = getRealClassName(point);
        // String method = request.getMethod();
        String methodName = getMethodName(point);
        Object[] args = point.getArgs();

        //从切面织入点处通过反射机制获取织入点处的方法（被拦截的方法）
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        StringBuilder param = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            param.append(parameters[i].getName() + ":" + JSON.toJSONString(args[i]));
            if (i < parameters.length - 1) {
                param.append(",");
            }

        }
        Class<?> returnType= method.getReturnType();
        logger.info("service调用开始 ->ip:{} ->调用类：{} ->方法：{} ->参数：{} ->返回值类型：{}", ip, className, methodName, param.toString(),JSON.toJSONString(returnType));
        Object result = point.proceed();//放行原方法
        logger.info("service调用结束 ->ip:{} ->调用类：{} ->方法：{}->参数：{} ->执行结果：{}", ip, className, methodName, param.toString(), JSON.toJSONString(result));
        // logger.info("环绕通知退出方法");

        return result;
    }


    //获取输入参数，此处为string类型的，参数名称与方法中的名称相同,如果不获取输入参数，可以不要
    @Before("controllerPoint()")  //指定了切点
    public void sayHello( JoinPoint point) {
        // logger.info("注解类型前置通知");
    }

    //最终通知，方法执行成功或者异常，都会执行此方法
    @After("controllerPoint()")
    public void sayGoodbeyAfter(JoinPoint point) {
        // logger.info("注解类型最终通知");
    }


    //后置通知，方法执行成功之后，会执行此方法，如果方法执行出现异常，那么就不会执行此方法，returning 是切点方法的返回值
    @AfterReturning(value = "controllerPoint()", returning = "resultObject")
    public void sayGoodbeyAfterReturn(JoinPoint joinPoint, Object resultObject) throws Throwable {
        // logger.info("注解类型后置通知");
        // logger.info("执行结果:" + JSON.toJSONString(resultObject));
    }



    /**
     * 异常通知,切点方法抛出异常时执行这里
     */

    @AfterThrowing(value = "controllerPoint()", throwing = "ex")
    public void throwingMethod(Throwable ex) {
        logger.info(ex.getMessage());
    }

    /**
     * 获取被代理对象的真实类全名
     *
     * @param point 连接点对象
     * @return 类全名
     */

    private String getRealClassName(JoinPoint point) {
        return point.getTarget().getClass().getName();
    }

    /**
     * 获取代理执行的方法名
     *
     * @param point 连接点对象
     * @return 调用方法名
     */

    private String getMethodName(JoinPoint point) {
        return point.getSignature().getName();
    }


    private String getReturnType(JoinPoint point) {
        //从切面织入点处通过反射机制获取织入点处的方法（被拦截的方法）
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            String parameterName = parameter.getName();
            System.out.println("修改不一样的地方1");
            System.out.println("不一样的地方2");
            System.out.println("不一样的地方3");
            
        }
        Object[] args = point.getArgs();
        if (args.length == parameters.length) {
            System.out.println("确实一样长1");
            System.out.println("一样长2");
            System.out.println("一样长3");
           
        }
        String declaringTypeName = point.getSignature().getDeclaringTypeName();
        System.out.println("stash6");
        return declaringTypeName;
    }
   
}

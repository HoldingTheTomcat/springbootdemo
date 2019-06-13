package com.ling.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ling.common.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author TianHeLing
 * @Description
 * @date 2018/12/1
 */

// @Aspect
// @Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal();
    ThreadLocal<String> uuid = new ThreadLocal();
    
    //定义一个切点，格式就是按切点对应的匹配格式
    @Pointcut("execution(* com..controller..*.*(..))")
    public void controllerPoint() {
    }//controllerPoint就是切点名，名字任意 

    @Pointcut("execution(* com..service.serviceImpl..*.*(..))")
    public void servicePoint() {
    }

    @Pointcut("execution(* com..controller..*.*(..))||execution(* com..service.serviceImpl..*.*(..))")
    public void commonPoint(){
        
    }

    /**
     * 注解类型前置通知
     *
     * @param point
     */
    @Before("commonPoint()")  //指定了切点
    public void sayHello(JoinPoint point) {
        this.startTime.set(System.currentTimeMillis());
        Boolean aBoolean = this.validateIsController(point);
        loggerPrint(point, aBoolean);
    }


    //后置通知，方法执行成功之后，会执行此方法，如果方法执行出现异常，那么就不会执行此方法，returning 是切点方法的返回值
    @AfterReturning(value = "controllerPoint()", returning = "resultObject")
    public void controllerAfterReturn(JoinPoint joinPoint, Object resultObject) throws Throwable {
        String resultString = "";
        if (resultObject != null) {
            resultString = JSON.toJSONString(resultObject);
        }
        long endTime = System.currentTimeMillis();
        logger.info("uuid:{}->controller调用结束 ->结果：{}", uuid.get(), resultString);
        logger.info("uuid:{}->请求执行总时间：{}ms", uuid.get(),(endTime - startTime.get()));
    }

    //后置通知，方法执行成功之后，会执行此方法，如果方法执行出现异常，那么就不会执行此方法，returning 是切点方法的返回值
    @AfterReturning(value = "servicePoint()", returning = "resultObject")
    public void serviceAfterReturn(JoinPoint joinPoint, Object resultObject) throws Throwable {
        String resultString = "";
        if (resultObject != null) {
            resultString = JSON.toJSONString(resultObject);
        }
        long endTime = System.currentTimeMillis();
        logger.info("uuid:{}->service调用结束->结果：{}", uuid, resultString);
        logger.info("请求执行总时间：{}ms", (endTime - startTime.get()));
    }


    //最终通知，方法执行成功或者异常，都会执行此方法
    @After("commonPoint()")
    public void sayGoodbeyAfter(JoinPoint point) {
        
    }
    
    private void loggerPrint(JoinPoint point, boolean isController) {
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
        // String uuid = loggerMethodInfo2(ip, className, methodName, args, signature);
        String[] parameterNames = signature.getParameterNames();
        Class[] parameterTypes = signature.getParameterTypes();

        StringBuilder paramName = new StringBuilder();
        Class<?> returnType = signature.getReturnType();
        String returnTypeString = returnType.toString();
        String returnTypeName = returnTypeString.substring(returnTypeString.lastIndexOf('.') + 1, returnTypeString.length());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (isController) {
            logger.info("uuid:{}->controller调用开始", uuid);
            logger.info("请求地址：{}", request.getRequestURI());
        } else {
            logger.info("uuid:{}->service 调用开始", uuid);
        }

        logger.info("ip:{}", ip);
        logger.info("调用类：{}", className);
        logger.info("返回值类型：{}", returnType);
        for (int i = 0; i < parameterNames.length; i++) {
            Class parameterTypeClass = parameterTypes[i];
            String typeClassString = parameterTypeClass.toString();
            String parameterType = typeClassString.substring(typeClassString.lastIndexOf('.') + 1, typeClassString.length());
            paramName.append(parameterType + " " + parameterNames[i]);
            if (i < parameterNames.length - 1) {
                paramName.append(",");
            }
        }
        logger.info("方法：{} {}({})", returnTypeName, methodName, paramName.toString());
        paramType2(args, parameterNames, parameterTypes );
        this.uuid.set(uuid);
    }


    private void paramType1(Object[] args, String[] parameterNames) {
        Map<String, Object> parameterMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            if (args[i] != null) {
                parameterMap.put(parameterNames[i], args[i]);
            } else {
                parameterMap.put(parameterNames[i], "null");
            }
        }
        logger.info("参数: {}", JSON.toJSONString(parameterMap, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));
    }

    private void paramType2(Object[] args, String[] parameterNames, Class[] parameterTypes) {
        for (int i = 0; i < parameterNames.length; i++) {
            if (isLog(parameterTypes[i])){
                logger.info("参数: {}:{}", parameterNames[i], JSON.toJSONString(args[i], SerializerFeature.WriteMapNullValue));
            }
        }
    }

    private boolean isLog(Class c){
        if (c == HttpServletRequest.class){
            return false;
        }
        return true;
    }
    private void paramType3(Object[] args, String[] parameterNames) {
        StringBuilder param = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            param.append(parameterNames[i] + ":" + JSON.toJSONString(args[i], SerializerFeature.WriteMapNullValue));
            if (i < parameterNames.length - 1) {
                param.append(",");
            }
        }
        logger.info("参数: {}", param.toString());
    }

    //根据类上是否有@RestController、@Controller判断是否是controller
    private Boolean validateIsController(JoinPoint joinPoint){
        Boolean isController = false;
        //获取目标对象的类
        Class<?> aClass = joinPoint.getTarget().getClass();
        RestController restController = aClass.getAnnotation(RestController.class);
        if (restController != null){
            isController = true;
        }
        Controller controller = aClass.getAnnotation(Controller.class);
        if (controller != null) {
            isController = true;
        }
        return isController;
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

}
package com.ling;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author TianHeLing
 * @Description
 * @date 2022-03-29
 */
public class ProxyDemo {

    interface Car {
        void run();
        void jump();
    }
    static class MyCar implements Car {
        @Override
        public void run() {
            System.out.println("跑");
        }
        @Override
        public void jump() {
            System.out.println("跳");

        }
    }

    public static void main(String[] args) {
        //把实例car创建出来 。被代理类实例,匿名内部类调用外部的资源，这个资源必须使用final修饰
        final Car car = new MyCar();
        // proxyCar是代理商，多态。内部类
        Car proxyCar = (Car) Proxy.newProxyInstance(
                ProxyDemo.class.getClassLoader(), //当前类对象的类加载器
                car.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //获得方法名
                        if ("jump".equals(method.getName())) {
                            System.out.println("飞");
                            return null;  //如果return null 那么目标方法就不会执行了
                        }
                        //如果遇到不需要增强的方法 直接放行  相当于执行以前的方法
                        return method.invoke(car, args); //执行原来的方法
                    }
                });
        proxyCar.run();
        proxyCar.jump(); //将方法改成飞 改装jump方法。
    }
}

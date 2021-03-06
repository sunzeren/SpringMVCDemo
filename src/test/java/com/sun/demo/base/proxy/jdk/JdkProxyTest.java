package com.sun.demo.base.proxy.jdk;

import com.sun.demo.base.proxy.OtherInterface;
import com.sun.demo.base.proxy.ProxyObject;
import com.sun.demo.base.proxy.SayHello;

import java.lang.reflect.Proxy;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/7 15:16
 * jdk的动态代理实现
 * jdk的动态代理只能代理实现接口
 *
 * jdk 动态代理需要相关类:
 * InvocationHandler:给代理对象增强的类,需要实现此接口
 * Proxy:
 * 用来创建代理对象,
 * 判断一个类是否为代理对象,
 * 获取一个代理对象的增强器(InvocationHandler),
 * 获取代理对象的class对象
 */
public class JdkProxyTest {

    public static void main(String[] args) {

        // 创建一个代理实现实例
        final ProxyInvocationHandlerTest proxyInvocationHandlerTest = new ProxyInvocationHandlerTest(new ProxyObject() {
            //@Override
            //public void say(String text) {
            //    System.out.println("You say:" + text);
            //}
        });

        // 返回代理对象
        // 此处的第二个参数为代理对象实现的接口,因为一个类可以实现多个接口所以这里是个数组
        // 注:jdk的代理只能基于接口 ,如需基于一个实例实现代理,则需要使用cglib
        final Object o = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[]{SayHello.class, OtherInterface.class}, proxyInvocationHandlerTest);

        if (o instanceof SayHello) {
            ((SayHello) o).say("I love you");
        }

        if (o instanceof OtherInterface) {
            //proxyInvocationHandlerTest.changeTarget((OtherInterface) () -> { });
            ((OtherInterface) o).testMethod();
        }
    }

}

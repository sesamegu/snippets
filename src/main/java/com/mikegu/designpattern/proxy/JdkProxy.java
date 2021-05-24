package com.mikegu.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类说明:使用jdk自带的动态代理，使用泛型T来指定接口类型，便于成为一个通用的类。
 *
 * @author guhaiquan 2021/5/20
 */
public class JdkProxy<T> implements InvocationHandler {

    private T beProxy;


    public JdkProxy(T beProxy) {
        this.beProxy = beProxy;
    }

    public static void main(String[] args) {
        Actress proxy = new JdkProxy<Actress>(new Yangmi()).getProxy();
        proxy.perform();

        //java.lang.ClassCastException
        //((Yangmi)proxy).sing();


    }

    public T getProxy() {
        Object o = Proxy.newProxyInstance(beProxy.getClass().getClassLoader(),
            beProxy.getClass().getInterfaces(), this);
//        System.out.println(o.getClass());
//        Class abc = o.getClass().getSuperclass();
//        while (abc != Object.class) {
//            System.out.println(abc.getClass().getSuperclass());
//            abc = abc.getSuperclass();
//        }
        return (T) o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(beProxy, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("请找经纪人协商商务");
    }

    private void after() {
        System.out.println("请付费给经纪人");
    }

}

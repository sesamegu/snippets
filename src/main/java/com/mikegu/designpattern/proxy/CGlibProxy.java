package com.mikegu.designpattern.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 类说明:使用Cglib来做Proxy，CGlib通过修改字节码来做增强
 *
 * @author guhaiquan 2021/5/24
 */
public class CGlibProxy implements MethodInterceptor {

    public static void main(String[] args) {
        Yangmi yangmi = new CGlibProxy().getProxy(Yangmi.class);
        yangmi.perform();
        yangmi.sing();
    }


    public <T> T getProxy(Class<T> clazz) {
        return (T) Enhancer.create(clazz, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
//        method.invoke(o, objects); 这样调用会进入递归调用栈溢出 java.lang.StackOverflowError
        Object retVal = methodProxy.invokeSuper(o, objects);
        after();
        return retVal;
    }

    private void before() {
        System.out.println("请找经纪人协商商务");
    }

    private void after() {
        System.out.println("请付费给经纪人");
    }

}

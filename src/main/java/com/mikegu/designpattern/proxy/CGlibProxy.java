package com.mikegu.designpattern.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * ��˵��:ʹ��Cglib����Proxy��CGlibͨ���޸��ֽ���������ǿ
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
//        method.invoke(o, objects); �������û����ݹ����ջ��� java.lang.StackOverflowError
        Object retVal = methodProxy.invokeSuper(o, objects);
        after();
        return retVal;
    }

    private void before() {
        System.out.println("���Ҿ�����Э������");
    }

    private void after() {
        System.out.println("�븶�Ѹ�������");
    }

}

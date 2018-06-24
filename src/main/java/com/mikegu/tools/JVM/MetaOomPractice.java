package com.mikegu.tools.JVM;

import javassist.ClassPool;

/**
 * 类说明: 演示Meta区的OOM异常(在java6这个case会抛perm区oom异常）
 * JVM启动参数  -XX:MaxMetaspaceSize=20M   -XX:+HeapDumpOnOutOfMemoryError
 * Java 8 彻底将永久代 (PermGen) 移除出了 HotSpot JVM，PermGen 最终被移除，方法区移至 Metaspace，字符串常量移至 Java Heap
 *
 * @author guhaiquan 2018/6/21
 */
public class MetaOomPractice {

    public static void main(String[] args) throws Exception {

        for (int i = 0; ; i++) {
            generate("PermOom class" + i);
        }
    }

    public static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }

}


//    Exception in thread "main" javassist.CannotCompileException: by java.lang.OutOfMemoryError: Metaspace
//    at javassist.ClassPool.toClass(ClassPool.java:1085)
//    at javassist.ClassPool.toClass(ClassPool.java:1028)
//    at javassist.ClassPool.toClass(ClassPool.java:986)
//    at javassist.CtClass.toClass(CtClass.java:1079)
//    at com.mikegu.tools.JVM.PermOomPractice.generate(PermOomPractice.java:23)
//    at com.mikegu.tools.JVM.PermOomPractice.main(PermOomPractice.java:15)
//    Caused by: java.lang.OutOfMemoryError: Metaspace
//    at java.lang.ClassLoader.defineClass1(Native Method)
//    at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
//    at java.lang.ClassLoader.defineClass(ClassLoader.java:642)
//    at sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
//    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//    at java.lang.reflect.Method.invoke(Method.java:498)
//    at javassist.ClassPool.toClass2(ClassPool.java:1098)
//    at javassist.ClassPool.toClass(ClassPool.java:1079)
//    ... 5 more

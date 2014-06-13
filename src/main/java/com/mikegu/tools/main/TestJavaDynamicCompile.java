package com.mikegu.tools.main;

import java.lang.reflect.Method;

import com.mikegu.tools.compile.DynamicClassLoader;
import com.mikegu.tools.compile.ScriptCompilerHelper;

/**
 * 知识点1：
 * 对与多个定义的URLClassLoader，如果一个URLClassLoader加载了一个class；第二URLClassLoader如果再次尝试加载这个类,<br>
 * 会重新加载这个类，而不是复用前面的类定义。原因是第一个URLClassLoader的类空间对第二个URLClassLoader不可见，具体和ClassLoader<br>
 * 的加载机制有关（parent优先）.Root Bootstrap(null)--> Extension ExtClassLoader(sun.misc.Launcher$ExtClassLoader)--><br>
 * -->System AppClassLoader（sun.misc.Launcher$AppClassLoader）--> User defined ClassLoader<br>
 * 
 * 知识点2：
 * URLClassLoader显示主动加载一个类，不会自动加载这个类的内部类；在后续用到的时候，会通过这个URLClassLoader自动加载内部类<br>
 * 
 * @author mike
 * @version $Id: TestJavaDynamicCompile.java, v 0.1 2014年6月12日 上午9:10:50 mike Exp $
 */
public class TestJavaDynamicCompile {

    private static final String targetDir = "/home/mike/data/githome/my/snippets/src/main/resources/javafile/classes/";

    /**
     * 
     * @param args
     * @throws Throwable 
     */
    public static void main(String[] args) throws Throwable {

        ScriptCompilerHelper.compile(
            "/home/mike/data/githome/my/snippets/src/main/resources/javafile/src", targetDir
           );
        Method method1 = DynamicClassLoader.load(targetDir, "TestAbc");
        System.out.println(DynamicClassLoader.execute(method1, new Object[] {}));

        ScriptCompilerHelper.compile(
            "/home/mike/data/githome/my/snippets/src/main/resources/javafile/src", targetDir);

        System.out.println(DynamicClassLoader.execute(method1, new Object[] {}));

        Method method2 = DynamicClassLoader.load(targetDir, "TestAbc");
        System.out.println(DynamicClassLoader.execute(method2, new Object[] {}));

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        while (contextClassLoader != null) {
            System.out.println(contextClassLoader);
            contextClassLoader = contextClassLoader.getParent();
        }
    }

}

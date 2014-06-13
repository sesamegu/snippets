package com.mikegu.tools.main;

import java.lang.reflect.Method;

import com.mikegu.tools.compile.DynamicClassLoader;
import com.mikegu.tools.compile.ScriptCompilerHelper;

/**
 * ֪ʶ��1��
 * �����������URLClassLoader�����һ��URLClassLoader������һ��class���ڶ�URLClassLoader����ٴγ��Լ��������,<br>
 * �����¼�������࣬�����Ǹ���ǰ����ඨ�塣ԭ���ǵ�һ��URLClassLoader����ռ�Եڶ���URLClassLoader���ɼ��������ClassLoader<br>
 * �ļ��ػ����йأ�parent���ȣ�.Root Bootstrap(null)--> Extension ExtClassLoader(sun.misc.Launcher$ExtClassLoader)--><br>
 * -->System AppClassLoader��sun.misc.Launcher$AppClassLoader��--> User defined ClassLoader<br>
 * 
 * ֪ʶ��2��
 * URLClassLoader��ʾ��������һ���࣬�����Զ������������ڲ��ࣻ�ں����õ���ʱ�򣬻�ͨ�����URLClassLoader�Զ������ڲ���<br>
 * 
 * @author mike
 * @version $Id: TestJavaDynamicCompile.java, v 0.1 2014��6��12�� ����9:10:50 mike Exp $
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

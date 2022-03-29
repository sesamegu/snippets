package com.mikegu.tools.compile;

import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 *
 * @author haiquan.guhq
 * @version $Id: DynamicClassLoader.java, v 0.1 2014年6月12日 上午11:42:32 mike Exp $
 */
public class DynamicClassLoader {

    public static Method load(String targetDir, String className) throws ScriptException, SecurityException, NoSuchMethodException {
        // 1.3:构建失败，抛出ScriptRuntimeException

        URLClassLoader classLoader = ScriptCompilerHelper.getClassLoader(targetDir);

        Class<?> clazz = ScriptCompilerHelper.getClass(className, classLoader);
        Method scriptMethod = clazz.getMethod("abcd", null);
        //        Method scriptMethod = ScriptCompilerHelper.getMethod(null);
        scriptMethod.setAccessible(true);

        return scriptMethod;
    }

    public static Object execute(Method scriptMethod, Object[] args) throws ScriptException {
        return ScriptCompilerHelper.invoke(scriptMethod, args);
    }

}

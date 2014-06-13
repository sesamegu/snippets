package com.mikegu.tools.compile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


/**
 * 脚本动态编译帮助类
 */
public class ScriptCompilerHelper {

    /**
     * 编译java文件
     * 
     * @param sourceDir
     *            -java源文件存放目录
     * @param targetDir
     *            -编译后class类文件存放目录
     * @return 编译结果,参见{@link CompileResult}
     * @throws IOException
     *             if StandardJavaFileManager.close happend error,throw
     *             IOException
     */
    public static CompileResult compile(String sourceDir, String targetDir) throws Throwable {

        // sourceDir is blank
        if (isBlank(sourceDir)) {
            throw new IllegalArgumentException("sourceDir is blank");
        }
        // targetDir is blank
        if (isBlank(targetDir)) {
            throw new IllegalArgumentException("targetDir is blank");
        }

        /*
         * 编译参数设置 -d:设置编译生成的classes文件存放目录 -cp:设置编译时依赖的classPath
         * -sourcepath:设置编译的源文件存放目录
         */
        String classPath = SystemClassPathFinder.findSystemClassPath();
        Iterable<String> options = null;
        if (isBlank(classPath)) {
            options = Arrays.asList("-d", targetDir, "-sourcepath", sourceDir);
        } else {
            options = Arrays.asList("-d", targetDir, "-sourcepath", sourceDir, "-cp",
                SystemClassPathFinder.findSystemClassPath());
        }
        // 创建classes输出文件夹
        FileOperateUtil.mkdirs(targetDir);
        // 再次编译
        return compileFiles(sourceDir, options);
    }

    /**
     * 取得某个className下的class
     * 
     * @param targetDir
     *            -存放class文件的目录地址
     * @param className
     *            -类名
     * @param classLoader
     *            -classLoader
     * @return
     * @throws ScriptException
     */
    public static Class<?> getClass(String className, URLClassLoader classLoader)
                                                                                                   throws ScriptException {
        try {
            return classLoader.loadClass(className);
        } catch (Exception e) {
            throw new ScriptException("get Class occour error", e);
        }
    }

    /**
     * 获取classLoader实例
     * 
     * @param targetDir
     * @return URL获取失败，返回NULL
     */
    public static URLClassLoader getClassLoader(String targetDir) {
        URL classpath;
        try {
            if (("" + targetDir.charAt(0)).equals(File.separator)) {
                classpath = new URL("file:" + targetDir + File.separator);
            } else {
                classpath = new URL("file:" + File.separator + targetDir + File.separator);
            }
        } catch (Exception e) {
            System.err.println("getClassLoader,classpath occour error,targetDir:" + targetDir + e);
            return null;
        }
        URLClassLoader classLoader = new URLClassLoader(new URL[] { classpath }, Thread
            .currentThread().getContextClassLoader());
        return classLoader;
    }

    /**
     * 取得Method实例
     * 
     * @param methodParam
     *            参见{@link MethodParam}
     * @return Method实例
     * @throws ScriptException
     *             if get method occour error
     */
    public static Method getMethod(MethodParam methodParam) throws ScriptException {
        try {
            return methodParam.getClazz().getMethod(methodParam.getMethodName(),
                methodParam.getParameterTypes());
        } catch (Exception th) {
            throw new ScriptException("get method occour error", th);
        }
    }

    /**
     * 执行Method，返回Method结果
     * 
     * @param methodParam
     *            参见{@link MethodParam}
     * @param args
     *            method 入参
     * @return 执行结果
     * @throws ScriptException
     *             if execute method invoke occour error
     */
    public static Object invoke(MethodParam methodParam, Object... args) throws ScriptException {
        try {
            return getMethod(methodParam).invoke(null, args);
        } catch (Exception e) {
            throw new ScriptException("execute method invoke occour error", e);
        }
    }

    /**
     * 执行Method，返回Method结果
     * 
     * @param method
     *            method实例
     * @param args
     *            入参
     * @return 执行结果
     * @throws ScriptException
     *             if execute invoke occour error
     */
    public static Object invoke(Method method, Object... args) throws ScriptException {
        try {
            return method.invoke(null, args);
        } catch (Exception e) {
            throw new ScriptException("execute invoke occour error", e);
        }
    }

    /**
     * 编译JAVA文件
     * 
     * @param sourceDir
     * @param options
     * @return
     * @throws IOException
     */
    private static CompileResult compileFiles(String sourceDir, Iterable<String> options)
                                                                                         throws IOException {

        List<File> sourceFiles = FileOperateUtil.findSourceFiles(sourceDir);

        // 获取编译器实例
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 获取标准文件管理器实例
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        // 获取要编译的编译单元
        Iterable<? extends JavaFileObject> compilationUnits = fileManager
            .getJavaFileObjectsFromFiles(sourceFiles);

        // 存放编译错误信息
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        try {
            CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics,
                options, null, compilationUnits);

            // 运行编译任务
            CompileResult compileResult = new CompileResult(compilationTask.call(), diagnostics);
            if (!compileResult.isCompileSuccess()) {
                System.err.println("compile error,sourceDir:" + sourceDir + " errorInfo:"
                             + compileResult.toString());
            }
            return compileResult;
        } finally {
            fileManager.close();
        }
    }

    private static boolean isBlank(String abc) {
        return abc == null ? true : abc.trim().equals("");
    }
}
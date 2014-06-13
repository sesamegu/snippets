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
 * �ű���̬���������
 */
public class ScriptCompilerHelper {

    /**
     * ����java�ļ�
     * 
     * @param sourceDir
     *            -javaԴ�ļ����Ŀ¼
     * @param targetDir
     *            -�����class���ļ����Ŀ¼
     * @return ������,�μ�{@link CompileResult}
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
         * ����������� -d:���ñ������ɵ�classes�ļ����Ŀ¼ -cp:���ñ���ʱ������classPath
         * -sourcepath:���ñ����Դ�ļ����Ŀ¼
         */
        String classPath = SystemClassPathFinder.findSystemClassPath();
        Iterable<String> options = null;
        if (isBlank(classPath)) {
            options = Arrays.asList("-d", targetDir, "-sourcepath", sourceDir);
        } else {
            options = Arrays.asList("-d", targetDir, "-sourcepath", sourceDir, "-cp",
                SystemClassPathFinder.findSystemClassPath());
        }
        // ����classes����ļ���
        FileOperateUtil.mkdirs(targetDir);
        // �ٴα���
        return compileFiles(sourceDir, options);
    }

    /**
     * ȡ��ĳ��className�µ�class
     * 
     * @param targetDir
     *            -���class�ļ���Ŀ¼��ַ
     * @param className
     *            -����
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
     * ��ȡclassLoaderʵ��
     * 
     * @param targetDir
     * @return URL��ȡʧ�ܣ�����NULL
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
     * ȡ��Methodʵ��
     * 
     * @param methodParam
     *            �μ�{@link MethodParam}
     * @return Methodʵ��
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
     * ִ��Method������Method���
     * 
     * @param methodParam
     *            �μ�{@link MethodParam}
     * @param args
     *            method ���
     * @return ִ�н��
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
     * ִ��Method������Method���
     * 
     * @param method
     *            methodʵ��
     * @param args
     *            ���
     * @return ִ�н��
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
     * ����JAVA�ļ�
     * 
     * @param sourceDir
     * @param options
     * @return
     * @throws IOException
     */
    private static CompileResult compileFiles(String sourceDir, Iterable<String> options)
                                                                                         throws IOException {

        List<File> sourceFiles = FileOperateUtil.findSourceFiles(sourceDir);

        // ��ȡ������ʵ��
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // ��ȡ��׼�ļ�������ʵ��
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        // ��ȡҪ����ı��뵥Ԫ
        Iterable<? extends JavaFileObject> compilationUnits = fileManager
            .getJavaFileObjectsFromFiles(sourceFiles);

        // ��ű��������Ϣ
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        try {
            CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics,
                options, null, compilationUnits);

            // ���б�������
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
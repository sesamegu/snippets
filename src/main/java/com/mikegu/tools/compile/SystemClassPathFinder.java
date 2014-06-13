package com.mikegu.tools.compile;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 系统依赖的ClassPath路径查找
 */
public class SystemClassPathFinder {
    /** JAVA_CLASS_PATH */
    private static final String JAVA_CLASS_PATH = "java.class.path";

    /** 系统运行时路径 */
    private String              systemRuntimeWorkPath;

    /** classpath路径 */
    private String              classPath       = null;

    /** coreTest */
    private boolean             coreTest        = false;

    /**
     * 系统依赖的ClassPath查找
     * 
     * @return
     */
    public synchronized String getSystemClassPath() {

        if (classPath == null) {

            //服务器环境
            if (!coreTest) {
                StringBuffer classPathBuf = new StringBuffer();
                for (String jarName : dependencyJarNames()) {
                    classPathBuf.append(File.pathSeparator);
                    classPathBuf.append(jarName);
                }
                classPath = classPathBuf.toString();

            }
            //本机环境
            else {
                Properties property = System.getProperties();
                classPath = File.pathSeparator + (String) property.get(JAVA_CLASS_PATH);
            }
        }

        return classPath;
    }

    /**
     * 系统依赖的Jar名称列表,包含绝对路径
     */
    public List<String> dependencyJarNames() {
        List<String> fileNames = new ArrayList<String>();
        FileOperateUtil.getFilesWithFilter(getAllJarPaths(), fileNames);
        return fileNames;
    }

    /**
     * 得到配置文件所有路径
     * @return
     */
    private String[] getAllJarPaths() {
        if (isBlank(systemRuntimeWorkPath)) {
            return new String[0];
        }
        return systemRuntimeWorkPath.split(",");
    }

    /**
     * Setter method for property <tt>systemRuntimeWorkPath</tt>.
     * 
     * @param systemRuntimeWorkPath value to be assigned to property systemRuntimeWorkPath
     */
    public void setSystemRuntimeWorkPath(String systemRuntimeWorkPath) {
        String workingArea = getApplicationWorkingArea();
        if (!isBlank(workingArea)) {
            this.systemRuntimeWorkPath = workingArea + ","
                                         + systemRuntimeWorkPath;
        } else {
            this.systemRuntimeWorkPath = systemRuntimeWorkPath;
        }
    }

    /**
     * 读取系统加载的类路径
     * 
     * @return 系统加载的类路径
     */
    private static String getApplicationWorkingArea() {
        ClassLoader loader = SystemClassPathFinder.class.getClassLoader();
        String workingArea = null;
        try {
            Method[] mds = loader.getClass().getMethods();
            Method method = null;
            for (Method m : mds) {
                if ("getApplicationWorkingArea".equals(m.getName())) {
                    method = m;
                    break;
                }
            }
            if (method != null) {
                workingArea = (String) method.invoke(loader, new Object[] {});
            }
        } catch (Exception e) {
            System.err.println("getApplicationWorkingArea,N" + e);
        }
        return workingArea;
    }

    /**
     * 获取系统ClassPath
     * 
     * @return
     */
    public static String findSystemClassPath() {
        return getInstance().getSystemClassPath();
    }

    /**
     * 获取单例helper
     * 
     * @return
     */
    public static SystemClassPathFinder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Getter method for property <tt>coreTest</tt>.
     * 
     * @return property value of coreTest
     */
    public boolean isCoreTest() {
        return coreTest;
    }

    /**
     * Setter method for property <tt>coreTest</tt>.
     * 
     * @param coreTest value to be assigned to property coreTest
     */
    public void setCoreTest(boolean coreTest) {
        this.coreTest = coreTest;
    }

    /**
     * Getter method for property <tt>classPath</tt>.
     * 
     * @return property value of classPath
     */
    public String getClassPath() {
        return classPath;
    }

    /**
     * Setter method for property <tt>classPath</tt>.
     * 
     * @param classPath value to be assigned to property classPath
     */
    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    /**
     * InstanceHolder
     * 
     */
    private static class InstanceHolder {
        /** 单例对象 */
        private static final SystemClassPathFinder INSTANCE = new SystemClassPathFinder();
    }

    private static boolean isBlank(String abc) {
        return abc == null ? true : abc.trim().equals("");
    }
}

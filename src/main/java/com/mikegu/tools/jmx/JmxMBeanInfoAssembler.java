package com.mikegu.tools.jmx;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jmx.export.assembler.AbstractConfigurableMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.AutodetectCapableMBeanInfoAssembler;
import org.springframework.util.ClassUtils;

/**
 * <p>
 * 根据Annotation规则暴露Mbean
 * JmxClass:必须可以加注任何class/interface上,由于spring有代理,建议加在interface上
 * JmxMethod:必须加在class的方法上,如果method覆盖,必须在该Method指定JmxMethod
 * </p>
 */
public class JmxMBeanInfoAssembler extends AbstractConfigurableMBeanInfoAssembler implements AutodetectCapableMBeanInfoAssembler {

    private Map<String, JmxClassInfo>   assemblerMBean  = new java.util.concurrent.ConcurrentHashMap<String, JmxClassInfo>();

    /**
     * <p>
     * 判断注解是否包含JmxClass,如果包含则加载所有JmxMethod; 加载过程中,必须注意通过Proxy代理生成的bean
     * </p>
     */
    public boolean includeBean(@SuppressWarnings("rawtypes") Class beanClass, String beanName) {
        if (beanClass == null)
            return false;

        if (beanClass.isInterface())
            return false;

        /** 通过Annotation判断* */
        boolean flag = isJmxClass(beanClass, beanName);

        if (flag)
            flag = registerMBean(beanClass, beanName);

        return flag;
    }

    private boolean registerMBean(Class<?> beanClass, String beanName) {
        boolean flag = false;

        JmxClassInfo info = new JmxClassInfo(beanClass);

        boolean allMethods = false;

        JmxClass jmxClass = beanClass.getAnnotation(JmxClass.class);

        if (jmxClass == null) {
            Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(beanClass);
            if (interfaces != null)
                for (Class<?> _interface : interfaces) {
                    Annotation[] _interfaceAnnotations = _interface.getAnnotations();
                    if (_interfaceAnnotations != null)
                        for (Annotation annotation : _interfaceAnnotations) {
                            if (annotation instanceof JmxClass) {
                                allMethods = ((JmxClass) annotation).allMethods();
                                break;
                            }
                        }
                }
        } else {
            allMethods = jmxClass.allMethods();
        }

        List<Method> methods = getPublicMethods(beanClass);
        if (methods != null)
            for (Method method : methods) {
                if (allMethods && !Modifier.isAbstract(method.getModifiers())) {
                    info.addMethod(method);
                    flag = true;
                    continue;
                }

                Annotation[] _interfaceAnnotations = method.getAnnotations();
                if (_interfaceAnnotations != null)
                    for (Annotation annotation : _interfaceAnnotations) {
                        if (annotation instanceof JmxMethod) {
                            info.addMethod(method);
                            flag = true;
                        }
                    }
            }

        if (flag)
            assemblerMBean.put(beanName, info);

        return flag;
    }

    private boolean isJmxClass(Class<?> beanClass, String beanName) {
        boolean jmxClass = _isJmxClass(beanClass);
        if (jmxClass)
            return jmxClass;

        /**
         * 判断子类是否实现了JmxClass
         */
        Class<?> superClass = beanClass;
        while ((superClass = superClass.getSuperclass()) != null) {
            if (superClass.getName().equalsIgnoreCase("java.lang.Object")) {
                return false;
            } else {
                if (_isJmxClass(superClass))
                    return true;
            }
        }

        return false;
    }

    private boolean _isJmxClass(Class<?> beanClass) {
        if (beanClass == null || beanClass.getName().equalsIgnoreCase("java.lang.Object"))
            return false;

        Annotation[] annotations = beanClass.getAnnotations();
        if (annotations != null)
            for (Annotation annotation : annotations) {
                if (annotation instanceof JmxClass) {
                    return true;
                }
            }

        /** 判断接口:代理类无法获得实际类型* */
        Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(beanClass);
        if (interfaces != null)
            for (Class<?> _interface : interfaces) {
                Annotation[] _interfaceAnnotations = _interface.getAnnotations();
                if (_interfaceAnnotations != null)
                    for (Annotation annotation : _interfaceAnnotations) {
                        if (annotation instanceof JmxClass) {
                            return true;
                        }
                    }
            }

        return false;
    }

    private boolean isJmxMethod(Method method, String beanKey) {
        JmxClassInfo info = assemblerMBean.get(beanKey);
        if (null == info)
            return false;
        return info.contain(method);
    }

    /**
     * 暴露所有方法
     */
    protected boolean includeOperation(Method method, String beanKey) {
        return isJmxMethod(method, beanKey);
    }

    /**
     * 暴露所有属性读方法
     */
    protected boolean includeReadAttribute(Method method, String beanKey) {
        return isJmxMethod(method, beanKey);
    }

    /**
     * 暴露所有属性写方法
     */
    protected boolean includeWriteAttribute(Method method, String beanKey) {
        return isJmxMethod(method, beanKey);
    }

    public static class JmxClassInfo {
        private Class<?>        clazz;
        private List<Method>    methods = new ArrayList<Method>();

        public JmxClassInfo(Class<?> clazz) {
            this.clazz = clazz;
        }

        public List<Method> getMethods() {
            return methods;
        }

        public void addMethod(Method method) {
            methods.add(method);
        }

        /**
         * <p>
         * 判断是否有相同的函数原型
         * </p>
         */
        public boolean contain(Method method) {
            if (method == null)
                return false;

            String methodName = method.getName();
            Class<?> paramters[] = method.getParameterTypes();

            for (Method _method : methods) {
                if (_method.getName().equals(methodName)) {

                    Class<?> _paramters[] = _method.getParameterTypes();

                    if (paramters == null && _paramters == null)
                        return true;

                    if (paramters == null || _paramters == null)
                        return false;

                    if (paramters.length != _paramters.length)
                        continue;

                    for (int i = 0; i < paramters.length; i++) {
                        if (!equal(paramters[i].getName(), _paramters[i].getName())) {
                            return false;
                        }
                    }

                    return true;
                }
            }

            return false;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }

    }
    
    public static boolean equal( Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
    
    
    
    public static final List<String> ignoreMethods = new ArrayList<String>();

    static {
        ignoreMethods.add("wait");
        ignoreMethods.add("hashCode");
        ignoreMethods.add("getClass");
        ignoreMethods.add("equals");
        ignoreMethods.add("toString");
        ignoreMethods.add("notify");
        ignoreMethods.add("notifyAll");
    }

    
    /**
     * <p>
     * 根据类获得所有的用户实现的公开的方法
     * </p>
     */
    public static List<Method> getPublicMethods(Class<?> clazz) {
        List<Method> methods = new ArrayList<Method>();
        if (!clazz.getName().startsWith("$Proxy")) {
            Method[] ms = clazz.getMethods();
            if (ms != null)
                for (Method m : ms) {
                    if ((m.getModifiers() & Modifier.PUBLIC) == java.lang.reflect.Modifier.PUBLIC) {
                        if (ignoreMethods.contains(m.getName()))
                            continue;
                        methods.add(m);
                    }
                }
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null) {
            for (Class<?> cla : interfaces) {
                Method[] ms = cla.getMethods();
                if (ms != null)
                    for (Method m : ms) {
                        if ((m.getModifiers() & Modifier.PUBLIC) == java.lang.reflect.Modifier.PUBLIC) {
                            if (ignoreMethods.contains(m.getName()))
                                continue;
                            methods.add(m);
                        }
                    }
            }
        } else {
            Method[] ms = clazz.getMethods();
            if (ms != null)
                for (Method m : ms) {
                    if ((m.getModifiers() & Modifier.PUBLIC) == java.lang.reflect.Modifier.PUBLIC) {
                        if (ignoreMethods.contains(m.getName()))
                            continue;
                        methods.add(m);
                    }
                }
        }
        return methods;
    }
    
    

}
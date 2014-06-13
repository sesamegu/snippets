package com.mikegu.tools.compile;
public class MethodParam {

    /** class */
    private final Class<?>[] parameterTypes;

    /** clazz */
    private final Class<?>   clazz;

    /** java方法名 */
    private final String     methodName;

    /**
     * MethodParam构建器
     * 
     * @author hongfa.sun
     * @version $Id: MethodParam.java, v 0.1 2012-11-22 下午10:09:34 hongfa.sun Exp $
     */
    public static class Builder {

        /** 方法名，必选参数*/
        private final String methodName;

        /** class */
        private Class<?>[]   parameterTypes;

        /** clazz */
        private Class<?>     clazz;

        /**
         * 构造函数
         * 
         * @param methodName    -方法名
         */
        public Builder(String methodName) {
            this.methodName = methodName;
        }

        /**
         * 构建方法入参类型
         * 
         * @param parameterTypes
         * @return
         */
        public Builder parameterTypes(Class<?>[] parameterTypes) {
            this.parameterTypes = parameterTypes;
            return this;
        }

        /**
         * 构建类
         * 
         * @param clazz
         * @return
         */
        public Builder clazz(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        /**
         * 构建{@link MethodParam}
         * 
         * @return
         */
        public MethodParam build() {
            return new MethodParam(this);
        }
    }

    /**
     * 私有构造器
     * 
     * @param builder
     */
    private MethodParam(Builder builder) {
        this.parameterTypes = builder.parameterTypes;
        this.clazz = builder.clazz;
        this.methodName = builder.methodName;
    }

    /**
     * 获取Builder 对象
     * 
     * @param methodName
     * @return
     */
    public static Builder getBuilderInstance(String methodName) {
        return new MethodParam.Builder(methodName);
    }

    /**
     * Getter method for property <tt>parameterTypes</tt>.
     * 
     * @return property value of parameterTypes
     */
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    /**
     * Getter method for property <tt>methodName</tt>.
     * 
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Getter method for property <tt>clazz</tt>.
     * 
     * @return property value of clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }

}

package com.mikegu.tools.compile;
public class MethodParam {

    /** class */
    private final Class<?>[] parameterTypes;

    /** clazz */
    private final Class<?>   clazz;

    /** java������ */
    private final String     methodName;

    /**
     * MethodParam������
     * 
     * @author hongfa.sun
     * @version $Id: MethodParam.java, v 0.1 2012-11-22 ����10:09:34 hongfa.sun Exp $
     */
    public static class Builder {

        /** ����������ѡ����*/
        private final String methodName;

        /** class */
        private Class<?>[]   parameterTypes;

        /** clazz */
        private Class<?>     clazz;

        /**
         * ���캯��
         * 
         * @param methodName    -������
         */
        public Builder(String methodName) {
            this.methodName = methodName;
        }

        /**
         * ���������������
         * 
         * @param parameterTypes
         * @return
         */
        public Builder parameterTypes(Class<?>[] parameterTypes) {
            this.parameterTypes = parameterTypes;
            return this;
        }

        /**
         * ������
         * 
         * @param clazz
         * @return
         */
        public Builder clazz(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        /**
         * ����{@link MethodParam}
         * 
         * @return
         */
        public MethodParam build() {
            return new MethodParam(this);
        }
    }

    /**
     * ˽�й�����
     * 
     * @param builder
     */
    private MethodParam(Builder builder) {
        this.parameterTypes = builder.parameterTypes;
        this.clazz = builder.clazz;
        this.methodName = builder.methodName;
    }

    /**
     * ��ȡBuilder ����
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

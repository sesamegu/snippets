package com.mikegu.tools.jmx;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Ƿ���Ҫ��¶��JmxClass
 */
@Target({ TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface JmxClass {
	boolean allMethods() default false;
}

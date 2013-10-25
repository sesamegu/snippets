package com.mikegu.tools.jmx;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否需要暴露成JmxClass
 */
@Target({ TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface JmxClass {
	boolean allMethods() default false;
}

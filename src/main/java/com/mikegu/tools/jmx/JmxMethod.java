package com.mikegu.tools.jmx;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否需要暴露成JmxMethod
 */
@Target( { METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface JmxMethod {

}

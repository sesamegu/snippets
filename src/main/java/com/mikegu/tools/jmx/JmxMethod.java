package com.mikegu.tools.jmx;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Ƿ���Ҫ��¶��JmxMethod
 */
@Target( { METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface JmxMethod {

}

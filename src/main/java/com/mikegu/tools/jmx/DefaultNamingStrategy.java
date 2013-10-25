/**
 * 
 */
package com.mikegu.tools.jmx;

import java.util.Hashtable;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.jmx.export.naming.ObjectNamingStrategy;

public class DefaultNamingStrategy implements ObjectNamingStrategy {

    /* 默认的Type */
    private final static String DEFAULT_TYPE = "defaultType";
    /* type的key */
    private final String TYPE_KEY = "type";
    /* name的key */
    private final String ID_KEY = "id";

    /*
     * 包名，如果没有配置，将使用类的package名
     */
    private String packageName;
    /*
     * 类型，如果没有，则使用默认的type
     */
    private String type = DEFAULT_TYPE;

    public ObjectName getObjectName(Object managedBean, String beanKey)
            throws MalformedObjectNameException {
        String domain = packageName;
        if (domain == null) {
            domain = managedBean.getClass().getPackage().getName();
        }
        Hashtable<String, String> properties = new Hashtable<String, String>();

        properties.put(TYPE_KEY, type);
        properties.put(ID_KEY, beanKey);

        return ObjectName.getInstance(domain, properties);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

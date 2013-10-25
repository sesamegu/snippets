package com.mikegu.tools.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * 类 AccessJmxClient 的实现描述：TODO 类实现描述
 * @author 听雷
 * 2013年10月24日下午3:23:11
 */
public class JmxAccessClient {

    public static String invokeJmx(String ip, String connectTimeout, String port,
                                    String objectName, String methodName, String[] arguments)
            throws MalformedURLException, IOException, MalformedObjectNameException,
            InstanceNotFoundException, IntrospectionException, ReflectionException, MBeanException {

        JMXConnector connector = null;
        try {

            final JMXServiceURL address = new JMXServiceURL("service:jmx:rmi://" + ip
                    + "/jndi/rmi://" + ip + ":" + port + "/defaultConnector");

            if (isEmpty(connectTimeout)) {
                connector = JMXConnectorFactory.connect(address);
            } else {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<JMXConnector> future = executor.submit(new Callable<JMXConnector>() {
                    public JMXConnector call() throws IOException {
                        return JMXConnectorFactory.connect(address);
                    }
                });
                connector = future.get(Integer.valueOf(connectTimeout), TimeUnit.MILLISECONDS);
            }
            MBeanServerConnection mbs = connector.getMBeanServerConnection();

            ObjectName mxbeanName = new ObjectName(objectName); // com.taobao.wlb:id=testJmxMBean,type=mywlb

            MBeanInfo mbeanInfo = mbs.getMBeanInfo(mxbeanName);
            MBeanOperationInfo[] opeInfos = mbeanInfo.getOperations();
            for (MBeanOperationInfo o : opeInfos) {
                if (o.getName().equals(methodName)) {
                    int length = o.getSignature().length;
                    Object[] params = new Object[length];
                    String[] typeNames = new String[length];
                    for (int i = 0; i < length; i++) {
                        if ("boolean".equals(o.getSignature()[i].getType())) {
                            params[i] = Boolean.valueOf(arguments[i]);
                        } else if ("int".equals(o.getSignature()[i].getType())) {
                            params[i] = Integer.valueOf(arguments[i]);
                        } else if ("java.lang.Integer".equals(o.getSignature()[i].getType())) {
                            params[i] = Integer.valueOf(arguments[i]);
                        } else if ("long".equals(o.getSignature()[i].getType())) {
                            params[i] = Long.valueOf(arguments[i]);
                        } else if ("java.lang.Long".equals(o.getSignature()[i].getType())) {
                            params[i] = Long.valueOf(arguments[i]);
                        } else if ("double".equals(o.getSignature()[i].getType())) {
                            params[i] = Double.valueOf(arguments[i]);
                        } else if ("java.lang.Double".equals(o.getSignature()[i].getType())) {
                            params[i] = Double.valueOf(arguments[i]);
                        } else {
                            params[i] = arguments[i];
                        }
                        typeNames[i] = o.getSignature()[i].getType();
                    }

                    Object result = mbs.invoke(mxbeanName, methodName, params, typeNames);
                    if (result == null)
                        return "调用无结果返回";
                    return isEmpty(result.toString()) ? "调用无结果返回或为空字符串" : result
                            .toString();
                }
            }
            return "Jmx对象中没有该方法";
        } catch (Exception e) {
            e.printStackTrace();
            return "执行JMX异常：" + e.getClass().toString() + ">" + e.getMessage();
        } finally {
            if (connector != null) {
                try {
                    connector.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isEmpty(String one) {
        if (one == null || "".equals(one)) {
            return true;
        } else {
            return false;
        }

    }
    
    
    public static void main(String[] args) throws MalformedObjectNameException, InstanceNotFoundException, IntrospectionException, MalformedURLException, ReflectionException, MBeanException, IOException {
        accessBeanFactory();
    }

    
    public static void accessNormal() throws MalformedObjectNameException, InstanceNotFoundException, IntrospectionException, MalformedURLException, ReflectionException, MBeanException, IOException{
        String ip = "127.0.0.1";
        String port = "2008";
        String connectTimeout = "";
        String objectName = "com.mike:type=abc,id=exportOneService";
        String methodName = "getCount";
        String[] params = null;

        
        System.out.println(invokeJmx(ip,connectTimeout , port,objectName,methodName,params));
    }
    
    
    public static void accessBeanFactory() throws MalformedObjectNameException, InstanceNotFoundException, IntrospectionException, MalformedURLException, ReflectionException, MBeanException, IOException{
        
        String ip = "127.0.0.1";
        String port = "2008";
        String connectTimeout = "";
        String objectName = "com.mike:type=abc,id=groovyRunner";
        String methodName = "execute";
        String[] params = {"beanFactory.getBean(\"oneService\").getCount();", "false"};

        
        System.out.println(invokeJmx(ip,connectTimeout , port,objectName,methodName,params));
        
    }
    
}

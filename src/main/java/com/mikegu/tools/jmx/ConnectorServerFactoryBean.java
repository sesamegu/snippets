package com.mikegu.tools.jmx;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

import javax.management.JMException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jmx.JmxException;
import org.springframework.jmx.support.JmxUtils;
import org.springframework.jmx.support.MBeanRegistrationSupport;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.util.StringUtils;

/**
 * 扩展了<code>spring</code>的<code>ConnectorServerFactoryBean</code>，可以基予配置的端口号和服务名，使用本地hostname和ip地址构建jndi方式的serviceUrl
 * <p>
 * 比如：<code>service:jmx:rmi://taobao-6990/jndi/rmi://10.1.19.35:1197/defaultConnector</code>
 */
public class ConnectorServerFactoryBean extends MBeanRegistrationSupport
        implements FactoryBean, InitializingBean, DisposableBean {

    private String serviceUrl;

    @SuppressWarnings("rawtypes")
    private Map jmxenvironment;

    private ObjectName objectName;

    private boolean threaded = false;

    private boolean daemon = false;

    private JMXConnectorServer connectorServer;

    // RMI端口，用于构建serviceUrl
    private String port = "1099";

    // 服务名称，用于构建serviceUrl
    private String serviceName = "defaultConnector";

    /**
     * Set the service URL for the <code>JMXConnectorServer</code>.
     */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    /**
     * Set the environment properties used to construct the
     * <code>JMXConnectorServer</code> as <code>java.util.Properties</code>
     * (String key/value pairs).
     */
    public void setJMXEnvironment(Properties environment) {
        this.jmxenvironment = environment;
    }

    /**
     * Set the environment properties used to construct the
     * <code>JMXConnector</code> as a <code>Map</code> of String keys and
     * arbitrary Object values.
     */
    @SuppressWarnings("rawtypes")
    public void setEnvironmentMap(Map environment) {
        this.jmxenvironment = environment;
    }

    /**
     * Set the <code>ObjectName</code> used to register the
     * <code>JMXConnectorServer</code> itself with the
     * <code>MBeanServer</code>, as <code>ObjectName</code> instance or as
     * <code>String</code>.
     * 
     * @throws MalformedObjectNameException
     *             if the <code>ObjectName</code> is malformed
     */
    public void setObjectName(Object objectName)
            throws MalformedObjectNameException {
        this.objectName = ObjectNameManager.getInstance(objectName);
    }

    /**
     * Set whether the <code>JMXConnectorServer</code> should be started in a
     * separate thread.
     */
    public void setThreaded(boolean threaded) {
        this.threaded = threaded;
    }

    /**
     * Set whether any threads started for the <code>JMXConnectorServer</code>
     * should be started as daemon threads.
     */
    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    public String getServiceUrl() {
        // 如果serviceUrl为空，则根据本地的hostname构造一个
        if (!StringUtils.hasText(serviceUrl)) {
            String hostName = "localhost";
            String hostAdd = "127.0.0.1";
            try {
                hostName = InetAddress.getLocalHost().getHostName();
                hostAdd = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
            serviceUrl = String.format(
                    "service:jmx:rmi://%s/jndi/rmi://%s:%s/%s", hostName,
                    hostAdd, port, serviceName);
        }
        return serviceUrl;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Start the connector server. If the <code>threaded</code> flag is set to
     * <code>true</code>, the <code>JMXConnectorServer</code> will be
     * started in a separate thread. If the <code>daemon</code> flag is set to
     * <code>true</code>, that thread will be started as a daemon thread.
     * 
     * @throws JMException
     *             if a problem occured when registering the connector server
     *             with the <code>MBeanServer</code>
     * @throws IOException
     *             if there is a problem starting the connector server
     */
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws JMException, IOException {
        if (this.server == null) {
            this.server = JmxUtils.locateMBeanServer();
        }

        // Create the JMX service URL.
        JMXServiceURL url = new JMXServiceURL(getServiceUrl());

        // Create the connector server now.
        this.connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(
                url, jmxenvironment, this.server);

        // Do we want to register the connector with the MBean server?
        if (this.objectName != null) {
            doRegister(this.connectorServer, this.objectName);
        }

        try {
            if (this.threaded) {
                // Start the connector server asynchronously (in a separate
                // thread).
                Thread connectorThread = new Thread() {
                    public void run() {
                        try {
                            connectorServer.start();
                        } catch (IOException ex) {
                            throw new JmxException(
                                    "Could not start JMX connector server after delay",
                                    ex);
                        }
                    }
                };

                connectorThread.setName("JMX Connector Thread ["
                        + getServiceUrl() + "]");
                connectorThread.setDaemon(this.daemon);
                connectorThread.start();
            } else {
                // Start the connector server in the same thread.
                this.connectorServer.start();
            }

            if (logger.isInfoEnabled()) {
                logger.info("JMX connector server started: "
                        + this.connectorServer);
            }
        }

        catch (IOException ex) {
            // Unregister the connector server if startup failed.
            unregisterBeans();
            throw ex;
        }
    }

    public Object getObject() {
        return this.connectorServer;
    }

    @SuppressWarnings("rawtypes")
    public Class getObjectType() {
        return (this.connectorServer != null ? this.connectorServer.getClass()
                : JMXConnectorServer.class);
    }

    public boolean isSingleton() {
        return true;
    }

    /**
     * Stop the <code>JMXConnectorServer</code> managed by an instance of this
     * class. Automatically called on <code>ApplicationContext</code>
     * shutdown.
     * 
     * @throws IOException
     *             if there is an error stopping the connector server
     */
    public void destroy() throws IOException {
        if (logger.isInfoEnabled()) {
            logger.info("Stopping JMX connector server: "
                    + this.connectorServer);
        }
        try {
            this.connectorServer.stop();
        } finally {
            unregisterBeans();
        }
    }

}

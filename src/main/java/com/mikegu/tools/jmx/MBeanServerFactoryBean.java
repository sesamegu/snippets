package com.mikegu.tools.jmx;


import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import org.springframework.beans.factory.FactoryBean;

public class MBeanServerFactoryBean implements FactoryBean{

	private static final MBeanServer server = ManagementFactory.getPlatformMBeanServer();

	public Object getObject() {
		return server;
	}

	public Class<?> getObjectType() {
		return (server != null ? server.getClass() : MBeanServer.class);
	}

	public boolean isSingleton() {
		return true;
	}

}

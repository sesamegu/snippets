package com.mikegu.tools.jmx;

import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

@JmxClass
public class GroovyRunner implements BeanFactoryAware {

	private BeanFactory			beanFactory;
	private ScriptEngineManager	factory;
	private ScriptEngine		engine;

	@PostConstruct
	public void init() {
		factory = new ScriptEngineManager();
		
		for (ScriptEngineFactory one :factory.getEngineFactories()){
		    System.out.println(one.toString());
		}
		
		engine = factory.getEngineByName("groovy");
		engine.put("beanFactory", beanFactory);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@JmxMethod
	public String execute(String script, boolean useParser) {
		try {
			if (useParser) {
				try {
					script = new String(script.getBytes("ISO-8859-1"), "GBK");
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}

			Object eval = engine.eval(script);
			return eval.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}
}

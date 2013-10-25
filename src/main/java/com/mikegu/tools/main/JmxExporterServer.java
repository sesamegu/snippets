package com.mikegu.tools.main;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 类 JmxExporterServer 的实现描述：TODO 类实现描述
 * @author 听雷
 * 2013年10月25日下午3:22:53
 */
public class JmxExporterServer {
    
    
    
    public static void main(String[] args) throws InterruptedException {
            // 启动Spring
            new ClassPathXmlApplicationContext("jmx-config.xml");
            
            System.out.println("start");
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
    }

}

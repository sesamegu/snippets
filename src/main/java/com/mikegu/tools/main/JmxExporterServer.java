package com.mikegu.tools.main;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �� JmxExporterServer ��ʵ��������TODO ��ʵ������
 * @author ����
 * 2013��10��25������3:22:53
 */
public class JmxExporterServer {
    
    
    
    public static void main(String[] args) throws InterruptedException {
            // ����Spring
            new ClassPathXmlApplicationContext("jmx-config.xml");
            
            System.out.println("start");
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
    }

}

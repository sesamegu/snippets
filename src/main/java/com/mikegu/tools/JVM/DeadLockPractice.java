package com.mikegu.tools.JVM;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明: 测试线程死锁。2个线程，有两个资源。
 * 第一个线程先获取资源1的锁，然后做一些业务操作，在获取资源2的锁；
 * 第二个线程先获取资源2的锁，然后做一些业务操作，在获取资源1的锁；
 *
 * 解决方案：锁有序
 *
 * @author guhaiquan 2018/6/21
 */
public class DeadLockPractice {


    private static Lock resourceOne = new ReentrantLock();
    private static Lock resourceTwo = new ReentrantLock();


    public void play(){
        Thread myThread = new Thread(new ThreadOne(), "threadOne");
        myThread.start();

        Thread myThread2 = new Thread(new ThreadTwo(), "threadTwo");
        myThread2.start();
    }


    public static void main(String[] args) {
        new DeadLockPractice().play();
    }


    class ThreadOne implements Runnable {

        @Override
        public void run() {

            try {
                //获取 资源1
                resourceOne.lock();

                //做一些业务事情
                try {
                    TimeUnit.SECONDS.sleep(5l);
                } catch (InterruptedException e) {
                }

                //再获取资源2
                resourceTwo.lock();
            } finally {
                resourceOne.unlock();
                resourceTwo.unlock();
            }
        }
    }

    class ThreadTwo implements Runnable {

        @Override
        public void run() {

            try {
                //获取 资源2
                resourceTwo.lock();

                //做一些事情
                try {
                    TimeUnit.SECONDS.sleep(5l);
                } catch (InterruptedException e) {
                }

                //再获取资源1
                resourceOne.lock();
            } finally {
                resourceTwo.unlock();
                resourceOne.lock();
            }
        }
    }

}

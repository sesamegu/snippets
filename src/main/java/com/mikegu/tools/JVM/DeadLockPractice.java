package com.mikegu.tools.JVM;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��˵��: �����߳�������2���̣߳���������Դ��
 * ��һ���߳��Ȼ�ȡ��Դ1������Ȼ����һЩҵ��������ڻ�ȡ��Դ2������
 * �ڶ����߳��Ȼ�ȡ��Դ2������Ȼ����һЩҵ��������ڻ�ȡ��Դ1������
 *
 * ���������������
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
                //��ȡ ��Դ1
                resourceOne.lock();

                //��һЩҵ������
                try {
                    TimeUnit.SECONDS.sleep(5l);
                } catch (InterruptedException e) {
                }

                //�ٻ�ȡ��Դ2
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
                //��ȡ ��Դ2
                resourceTwo.lock();

                //��һЩ����
                try {
                    TimeUnit.SECONDS.sleep(5l);
                } catch (InterruptedException e) {
                }

                //�ٻ�ȡ��Դ1
                resourceOne.lock();
            } finally {
                resourceTwo.unlock();
                resourceOne.lock();
            }
        }
    }

}

package com.mikegu.tools.JVM;

/**
 * ��˵��: CPU ���ߵ�case 100%
 * Linux: top -p <pid>  -H
 * Mac: top ����У���Ҫ������ǿ��Ĺ���
 *
 * @author guhaiquan 2018/6/22
 */
public class CpuHighPractice {

    public static void main(String[] args) {
        Thread myThread = new Thread(() -> new CpuHighPractice().practice(), "myThread ");
        myThread.start();
    }


    public void practice() {
        for (int i = 0; ; i++) {
            int t = i * 100 / 2;
        }
    }

}

package com.mikegu.tools.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * ��˵��: ����heap������OOM
 * Ϊ�˿��ٷ������⣬������������: -Xms256M  -Xmx256M  -XX:+HeapDumpOnOutOfMemoryError  -XX:-UseGCOverheadLimit
 *
 *
 * @author guhaiquan 2018/6/21
 */
public class HeapOomPractice {

    private List<AnObject> objects = new ArrayList<>();


    public static void main(String[] argv) {

        Thread myThread = new Thread(() -> new HeapOomPractice().play());
        myThread.setName("My thread");
        myThread.start();
    }

    public void play() {
        String data = "123232312333333333333333333333333333333";
        for (int i = 0; ; i++) {
            objects.add(new AnObject(data + i));
        }
    }


    class AnObject {

        private String filedOne;

        public AnObject(String filedOne) {
            this.filedOne = filedOne;
        }
    }

}



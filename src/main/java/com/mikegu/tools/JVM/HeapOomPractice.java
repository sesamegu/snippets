package com.mikegu.tools.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明: 测试heap区发生OOM
 * 为了快速发生问题，设置启动参数: -Xms256M  -Xmx256M  -XX:+HeapDumpOnOutOfMemoryError  -XX:-UseGCOverheadLimit
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
//
//objc[9611]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/bin/java (0x10eda34c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x110df54e0). One of the two will be used. Which one is undefined.
//    java.lang.OutOfMemoryError: Java heap space
//    Dumping heap to java_pid9611.hprof ...
//    Heap dump file created [295048318 bytes in 1.436 secs]
//    Exception in thread "My thread" java.lang.OutOfMemoryError: Java heap space
//    at com.mikegu.tools.JVM.HeapOomPractice.play(HeapOomPractice.java:28)
//    at com.mikegu.tools.JVM.HeapOomPractice.lambda$main$0(HeapOomPractice.java:20)
//    at com.mikegu.tools.JVM.HeapOomPractice$$Lambda$1/1922154895.run(Unknown Source)
//    at java.lang.Thread.run(Thread.java:748)
//
//    Process finished with exit code 0
//

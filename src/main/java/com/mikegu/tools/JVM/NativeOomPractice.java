package com.mikegu.tools.JVM;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 类说明: 堆外内存（native Oom ）异常 。 这类异常的原因一般是java中使用 DirectByteBuffer 和使用JNI对应的本地库出现内存问题
 *
 * @author guhaiquan 2018/6/22
 */
public class NativeOomPractice {

    public static void main(String[] args) {
        new Thread(() -> new NativeOomPractice().practice()).start();
    }

    public void practice() {
        List<ByteBuffer> buffers = new ArrayList<>();
        for (; ; ) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
            buffers.add(buffer);
        }
    }
}

//
//    Exception in thread "Thread-0" java.lang.OutOfMemoryError: Direct buffer memory
//    at java.nio.Bits.reserveMemory(Bits.java:694)
//    at java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:123)
//    at java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:311)
//    at com.mikegu.tools.JVM.NativeOomPractice.practice(NativeOomPractice.java:21)
//    at com.mikegu.tools.JVM.NativeOomPractice.lambda$main$0(NativeOomPractice.java:15)
//    at java.lang.Thread.run(Thread.java:748)

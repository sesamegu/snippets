package com.mikegu.tools.JVM;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * ��˵��: �����ڴ棨native Oom ���쳣 �� �����쳣��ԭ��һ����java��ʹ�� DirectByteBuffer ��ʹ��JNI��Ӧ�ı��ؿ�����ڴ�����
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

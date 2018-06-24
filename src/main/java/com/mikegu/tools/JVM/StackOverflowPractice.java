package com.mikegu.tools.JVM;

/**
 * 类说明: stack over flow 异常，thread的stack过深异常
 *
 * @author guhaiquan 2018/6/22
 */
public class StackOverflowPractice {


    public static void main(String[] args) {
//        long fib = new StackOverflowPractice().fib(10);
//        System.out.println("fib = " + fib);
        new StackOverflowPractice().fib(Long.MAX_VALUE);
    }

    public long fib(long n) {
        return n < 2 ? 1 : (fib(n - 1) + fib(n - 2));
    }

}
//
//    Exception in thread "main" java.lang.StackOverflowError
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
//    at com.mikegu.tools.JVM.StackOverflowPractice.fib(StackOverflowPractice.java:18)
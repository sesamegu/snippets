package com.mikegu.tools.JVM;

/**
 * ÀàËµÃ÷:
 *
 * @author guhaiquan 2018/6/21
 */
public class HeapOomPractice {


    public void play(){


    }




    public static void main(String[] argv){
//        new Thread(()->new HeapOomPractice().play());

    }






}


class AnObject{
    private String filedOne;

    public AnObject(String filedOne) {
        this.filedOne = filedOne;
    }
}
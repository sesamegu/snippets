package com.mikegu.tools.main;

/**
 * ภเหตร๗:
 *
 * @author guhaiquan 2018/6/18
 */
public class TestSystemProperties {


    //VM: -Dabc=dev1
    //program args: -Dabc=dev1

    public static void main(String[] args){

       String abc =  System.getProperty("abc");
        System.out.println(abc);

        System.out.println(args[0]);
    }

}

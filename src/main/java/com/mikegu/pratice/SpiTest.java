package com.mikegu.pratice;

import java.sql.Driver;
import java.util.ServiceLoader;

import com.sun.org.apache.bcel.internal.generic.DREM;

/**
 * Introduction:
 *
 * @author sesame 2022/8/3
 */
public class SpiTest {

    public static void main(String[] args) {

        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);

        for (Driver oneDrive: drivers){

            System.out.println(oneDrive.getClass().getName());
        }

    }
}

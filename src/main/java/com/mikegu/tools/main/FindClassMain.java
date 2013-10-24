package com.mikegu.tools.main;

import java.io.File;
import java.io.IOException;

import com.mikegu.tools.jardiff.ClassNameFinder;
import com.mikegu.tools.jardiff.ZipDecompression;

/**
 * 类 FindClass 的实现描述：在一堆jar中找到指定的类名
 * @author 听雷
 * 2013年10月21日下午3:47:25
 */
public class FindClassMain {
    
    
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("please input 1  dirs and name!");
            System.exit(1);
        }
        
        String firstInputPath = args[0];
        String className = args[1];
        
        File file = new File(firstInputPath);
        File[] listFiles = file.listFiles();
        for (File oneFile : listFiles) {
            if (oneFile.isFile() && oneFile.getAbsolutePath().endsWith(".jar")) {
                String jarName = oneFile.getName();
                ZipDecompression.decompression(oneFile.getAbsolutePath(), firstInputPath + "/tmp/"
                        + jarName); // 解压
                ClassNameFinder.find(jarName, firstInputPath + "/tmp/" + jarName, 1); // 查找
            }
        }
        
        ClassNameFinder.findName(className);
        
    }

}

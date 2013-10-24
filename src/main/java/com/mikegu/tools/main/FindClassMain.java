package com.mikegu.tools.main;

import java.io.File;
import java.io.IOException;

import com.mikegu.tools.jardiff.ClassNameFinder;
import com.mikegu.tools.jardiff.ZipDecompression;

/**
 * �� FindClass ��ʵ����������һ��jar���ҵ�ָ��������
 * @author ����
 * 2013��10��21������3:47:25
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
                        + jarName); // ��ѹ
                ClassNameFinder.find(jarName, firstInputPath + "/tmp/" + jarName, 1); // ����
            }
        }
        
        ClassNameFinder.findName(className);
        
    }

}

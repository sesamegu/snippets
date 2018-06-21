package com.mikegu.tools.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入两个txt文件，输出两个文件中的相同和差异
 */
public class TwoFileCamparer {

    private static String       file1   = "";
    private static String       file2   = "";

    private static List<String> data1   = new ArrayList<String>();
    private static List<String> data2   = new ArrayList<String>();

    private static List<String> inData1 = new ArrayList<String>();
    private static List<String> inData2 = new ArrayList<String>();
    private static List<String> common  = new ArrayList<String>();

    public static void readFileByLines(String fileName, boolean isFirst) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (isFirst) {
                    if (tempString.contains("=")){
                        data1.add(removeEmpty(tempString.trim().split("=")[0]));
                    }
                } else {
                    if (tempString.contains("=")) {
                        data2.add(removeEmpty(tempString.trim().split("=")[0]));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void compare() {
        //
        for (String oneString : data1) {
            boolean isContain = false;
            for (String twoString : data2) {
                if (twoString.equals(oneString)) {
                    isContain = true;
                    common.add(oneString);
                    break;
                }
            }
            if (!isContain) {
                inData1.add(oneString);
            }
        }

        for (String oneString : data2) {
            boolean isContain = false;
            for (String twoString : data1) {
                if (twoString.equals(oneString)) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                inData2.add(oneString);
            }
        }
    }

    private static void output() {
        System.out.println("in  file 1 : " + file1);
        for (String oneString : inData1) {
            System.out.println(oneString);
        }
        System.out.println("==========================================");
        System.out.println("in  file 2: " + file2);
        for (String oneString : inData2) {
            System.out.println(oneString);
        }
        System.out.println("==========================================");
        System.out.println("common:");
        for (String oneString : common) {
            System.out.println(oneString);
        }

    }

    private static String removeEmpty(String original) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < original.length(); i++) {
            char charAt = original.charAt(i);
            if (charAt == ' ' || charAt == '\t') {
                continue;
            }
            sb.append(charAt);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("args.length is not 2. args.length=" + args.length);
            return;
        }

        file1 = args[0];
        file2 = args[1];

        readFileByLines(args[0], true);
        readFileByLines(args[1], false);

        compare();

        output();
    }

}

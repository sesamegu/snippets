package com.mikegu.tools.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TxtProcess {


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            StringBuilder sb = new StringBuilder();
            Map<Integer,Integer> result = new HashMap<Integer, Integer>();
            while ((tempString = reader.readLine()) != null) {
//                int startpoint = tempString.indexOf("id");
//                int end = tempString.indexOf("\"",startpoint+4);
//                String name = tempString.substring(startpoint+4,end);
//                String result =   new   StringBuilder().append("$(\"#").append(name).append("\")").append(".html(\"\")").append(";").toString();
//                String result =   new   StringBuilder().append("$(\"#").append(name).append("\")").append(".html(data.)").append(";").toString();
//                    System.out.print(tempString+",");

                // 显示行号
//                System.out.println(result);
//                String[] strings = tempString.split(",");
//                Integer status  = Integer.parseInt(strings[7]);
//                if (result.containsKey(status)) {
//                    result.put(status, result.get(status) + 1);
//                }
//                else {
//                    result.put(status, 1);

//                }
//                sb.append(""+strings[1]+","+strings[2]rp_recharge.csv+"\n");
//
                sb.append("\""+tempString+"\",");


                //        $("#").html("");
//

                line++;
            }


            System.out.println(sb);

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

//    public static void readFileByLines(String fileName) {
//        File file = new File(fileName);
//        BufferedReader reader = null;
//        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            int line = 1;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                // 显示行号
//                System.out.print("\""+tempString+"\",");
////                    System.out.print(tempString+",");
//
//                line++;
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }



    public static Set<String> readSetFromFile(String fileName){
        Set<String> resultSet = new HashSet<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                boolean result =  resultSet.add(tempString.trim());
                if (result ==false){
                    System.out.println(tempString.trim());
                }
                line++;
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
        return resultSet;
    }




    public static void main(String[] args) {
        String fileName = "/Users/mike/51/codes/github/snippets/src/main/java/com/mikegu/tools/file/creditRefund.txt";
        TxtProcess.readFileByLines(fileName);
    }

}

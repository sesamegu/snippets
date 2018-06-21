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
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            StringBuilder sb = new StringBuilder();
            Map<Integer,Integer> result = new HashMap<Integer, Integer>();
            while ((tempString = reader.readLine()) != null) {
//                int startpoint = tempString.indexOf("id");
//                int end = tempString.indexOf("\"",startpoint+4);
//                String name = tempString.substring(startpoint+4,end);
//                String result =   new   StringBuilder().append("$(\"#").append(name).append("\")").append(".html(\"\")").append(";").toString();
//                String result =   new   StringBuilder().append("$(\"#").append(name).append("\")").append(".html(data.)").append(";").toString();
//                    System.out.print(tempString+",");

                // ��ʾ�к�
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
//            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            int line = 1;
//            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
//            while ((tempString = reader.readLine()) != null) {
//                // ��ʾ�к�
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
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
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

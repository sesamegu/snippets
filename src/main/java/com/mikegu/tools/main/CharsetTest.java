package com.mikegu.tools.main;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * 类 CharsetTest 的实现描述：验证字符集转化的类 2014年5月23日下午1:35:00
 */
public class CharsetTest {

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        // printCharset();
        switchCharset();
    }

    public static void printCharset() {
        SortedMap<String, Charset> charset = Charset.availableCharsets();
        for (String one : charset.keySet()) {
            System.out.println(one);
        }
        String csn = Charset.defaultCharset().name();
        System.out.println("defaultCharset:" + csn);
    }

    public static void switchCharset() throws UnsupportedEncodingException {
        String chinese = new String("中文");
        StringBuffer sb = stringToHexUnicode(chinese);
        System.out.println(sb.toString());

        byte[] gbkBytes = chinese.getBytes("GBK");
        System.out.println("Unicode to GBK bytes:\t" + bytesToHexString(gbkBytes));
        System.out.println("GBK bytes to Unicode:\t" + new String(gbkBytes, "GBK"));

        byte[] utf8Bytes = chinese.getBytes("UTF-8");
        System.out.println("Unicode to UTF-8 bytes:\t" + bytesToHexString(utf8Bytes));
        System.out.println("UTF-8 bytes to Unicode:\t" + new String(utf8Bytes, "UTF-8"));

        byte[] iso8859Bytes = chinese.getBytes("iso8859-1");
        System.out.println("Unicode to iso8859-1 bytes:\t" + bytesToHexString(iso8859Bytes));
        System.out.println("iso8859-1 bytes to Unicode:\t" + new String(iso8859Bytes, "iso8859-1"));

        System.out.println(chinese.equals(new String(chinese.getBytes("utf8"), "utf8")));

    }

    public static StringBuffer stringToHexUnicode(String chinese) {
        StringBuffer sb = new StringBuffer("Unicode: ");
        for (int i = 0; i < chinese.length(); i++) {
            char charAt = chinese.charAt(i);
            sb.append(Integer.toHexString(charAt));
        }
        return sb;
    }

    /*
     * Convert byte[] to hex
     * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     * @param src byte[] data
     * @return hex string
     */

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return "";
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}

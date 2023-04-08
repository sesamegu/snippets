package com.mikegu.algorithm.leecode;

/**
 * Introduction: 时间复杂度O(n)。在检查到相同字符时，开始字符可以直接跳跃到这个字符，后续字符继续向前
 *
 * @author sesame 2023/3/14
 */
public class LC003 {

    public static void main(String[] args) {

        String s = "au";
        System.out.println("Max length is " + new LC003().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int maxLength = 0;
        int startPoint = 0;
        int endPoint = 1;
        while (endPoint < s.length()) {

            String subString = s.substring(startPoint, endPoint);
            final int index = subString.indexOf(s.charAt(endPoint));
            if (index != -1) {
                if (subString.length() > maxLength) {
                    maxLength = subString.length();
                }
                startPoint = startPoint + index + 1;

            }
            endPoint++;
        }

        //包含最后一个字符的 字符串
        if (endPoint - startPoint > maxLength) {
            maxLength = endPoint - startPoint;
        }

        return maxLength;
    }

}

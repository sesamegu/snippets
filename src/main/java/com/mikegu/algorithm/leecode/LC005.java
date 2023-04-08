package com.mikegu.algorithm.leecode;

import java.util.Objects;

/**
 * Introduction:
 *
 * @author sesame 2023/3/27
 */
public class LC005 {

    public static void main(String[] args) {

        final String s = new LC005().longestPalindrome("babad");
        System.out.println("s = " + s);
    }

    public String longestPalindrome(String s) {
        if (Objects.isNull(s) || s.equals("")) {
            return null;
        }

        String maxString = s.substring(0, 1);
        //以字符为中心
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int low = i - 1;
            int high = i + 1;
            maxString = getString(s, maxString, length, low, high);
        }

        //以字符中间为中心
        for (int i = 0; i < length; i++) {
            int low = i - 1;
            int high = i;
            maxString = getString(s, maxString, length, low, high);
        }

        return maxString;
    }

    private String getString(String s, String maxString, int length, int low, int high) {
        while (low >= 0 && high < length) {
            if (s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > maxString.length()) {
                    maxString = s.substring(low, high + 1);
                }

                low--;
                high++;
            } else {
                break;
            }
        }
        return maxString;
    }

}

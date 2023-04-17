package com.mikegu.algorithm.leecode;

/**
 * Introduction: 这题括号匹配的定义在题目中没有说明清楚，做的时候想简单了。
 * 看了解答发现其实答案并不难。官方提供了三个方案，第三个方案比较容易理解和直接。
 * 第二方案也可以，通过栈来匹配。
 *
 * @author sesame 2023/4/16
 */
public class LC032 {

    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

}

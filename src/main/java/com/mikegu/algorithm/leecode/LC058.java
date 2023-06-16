package com.mikegu.algorithm.leecode;

/**
 * Introduction: 这用些java的内置函数
 *
 * @author sesame 2023/6/13
 */
public class LC058 {
    public int lengthOfLastWord(String s) {
        if (s == null | s.length() == 0) {
            return 0;
        }
        String s2 = s.trim();
        final int index = s2.lastIndexOf(' ');
        if (index == -1) {
            return s2.length();
        }

        return s2.length() - index - 1;

    }
}

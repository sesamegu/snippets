package com.mikegu.algorithm.leecode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Introduction: 滑动窗口 + Hash
 * 下面是官方的答案：优化点，左指针可以多跳几个，记住下
 *
 * @author sesame 2023/4/24
 */
public class LC076 {

    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindow(String s, String t) {
        //原始字符的集合
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = -1;
        int maxLength = Integer.MAX_VALUE, maxLeft = -1, maxRight = -1;
        int sLen = s.length();
        while (right < sLen) {
            //右指针往前
            ++right;
            //统计出现的字符
            if (right < sLen && ori.containsKey(s.charAt(right))) {
                cnt.put(s.charAt(right), cnt.getOrDefault(s.charAt(right), 0) + 1);
            }

            //有符合的后，左指针往前
            while (check() && left <= right) {
                if (right - left + 1 < maxLength) {
                    maxLength = right - left + 1;
                    maxLeft = left;
                    maxRight = left + maxLength;
                }
                if (ori.containsKey(s.charAt(left))) {
                    cnt.put(s.charAt(left), cnt.getOrDefault(s.charAt(left), 0) - 1);
                }
                ++left;
            }
        }
        return maxLeft == -1 ? "" : s.substring(maxLeft, maxRight);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}

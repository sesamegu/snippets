package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduction: 使用回溯的思想，递归找所有可能，找到一种后直接返回。这个执行消耗449ms
 * 使用动态规划算法的消耗时间为2ms
 *
 * 我们不妨换个角度考虑这个问题：字母 + 星号的组合在匹配的过程中，本质上只会有两种情况：
 * 1) 匹配 sss 末尾的一个字符，将该字符扔掉，而该组合还可以继续进行匹配；
 * 2) 不匹配字符，将该组合扔掉，不再进行匹配。
 *
 * 最终的状态转移方程如下：
 * f[i][j] =
 *
 * if (p[j] != ‘*’)   f[i−1][j−1],  matches(s[i],p[j])
 *                    false,        otherwise
 *
 *
 * if (p[j] == ‘*’)   f[i−1][j] or f[i][j−2], matches(s[i],p[j−1])
 *                    f[i][j−2],otherwise
 *
 * @author sesame 2023/4/10
 */
public class LC010 {

    public static void main(String[] args) {
        String s = "ab", p = ".*c";
        final boolean match = new LC010().isMatch(s, p);
        System.out.println("result is " + match);
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        //初始化所有状态为
        boolean[][] f = new boolean[m + 1][n + 1];
        //空对空为true
        f[0][0] = true;
        //按行依次赋予值
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                //为*的情况
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                // 为字母或者.的情况
                else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    //
    //public boolean isMatch(String s, String p) {
    //    List<Boolean> isFound = new ArrayList<>();
    //    isFound.add(new Boolean(false));
    //
    //    tryMatch(s, p, 0, 0, isFound);
    //
    //    return isFound.get(0);
    //
    //}
    //
    //private void tryMatch(String s, String p, int originalIndex, int regexIndex, List<Boolean> isFound) {
    //    if (isFound.get(0)) {
    //        return;
    //    }
    //    //都已经结束
    //    if (originalIndex == s.length() && regexIndex == p.length()) {
    //        isFound.set(0, true);
    //        return;
    //    }
    //    //正则结束，字符串没结束，肯定不匹配；原字符串结束，正则不结束，存在正则后面前匹配为空的情况
    //    if (regexIndex == p.length()) {
    //        return;
    //    }
    //
    //    //需要检查下一个字符是否为*
    //    boolean isNextStar;
    //    if (regexIndex + 1 == p.length()) {
    //        isNextStar = false;
    //    } else {
    //        isNextStar = p.charAt(regexIndex + 1) == '*';
    //    }
    //
    //    final char regexChar = p.charAt(regexIndex);
    //    if (isNextStar) {
    //        //处理0个情况
    //        tryMatch(s, p, originalIndex, regexIndex + 2, isFound);
    //        //输入保证*前面必有字符，且非*
    //        char last = p.charAt(regexIndex);
    //        if (last == '*') {
    //            throw new RuntimeException("**");
    //        } else if (last == '.') {
    //            // todo 这里可以再优化一点，跳到长度减去 正则表达后面的字符长度（需要考虑后面也有*的情况）
    //            for (int i = 0; i + originalIndex < s.length(); i++) {
    //                tryMatch(s, p, originalIndex + i + 1, regexIndex + 2, isFound);
    //            }
    //        } else {
    //            for (int i = 0; i + originalIndex < s.length(); i++) {
    //                //结束 或者 字符相同
    //                if (i + originalIndex == s.length() || s.charAt(originalIndex + i) == last) {
    //                    tryMatch(s, p, originalIndex + i + 1, regexIndex + 2, isFound);
    //                } else {
    //                    break;
    //                }
    //            }
    //        }
    //
    //    } else {
    //        //如果字符串已结束，直接返回
    //        if (originalIndex == s.length()) {
    //            return;
    //        }
    //        if (regexChar == '.') {
    //            tryMatch(s, p, originalIndex + 1, regexIndex + 1, isFound);
    //        } else if (regexChar == '*') {
    //            //这个情况再上面已处理
    //            throw new RuntimeException("*");
    //        } else {
    //            if (regexChar == s.charAt(originalIndex)) {
    //                tryMatch(s, p, originalIndex + 1, regexIndex + 1, isFound);
    //            } else {
    //                return;
    //            }
    //        }
    //    }
    //}
}

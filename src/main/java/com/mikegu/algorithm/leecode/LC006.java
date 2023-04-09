package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduction:  按顺序遍历字符串 s 时，每个字符 c 在 Z 字形中对应的 行索引 先从s1增大至 sn
 * 再从 sn减小至 s1s_1s 1
 *
 * 这题有点简单，直接看了题解，不自己写了
 *
 * @author sesame 2023/4/9
 */
public class LC006 {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) { res.append(row); }
        return res.toString();
    }

}

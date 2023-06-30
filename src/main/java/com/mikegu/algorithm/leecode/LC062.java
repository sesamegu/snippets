package com.mikegu.algorithm.leecode;

/**
 * Introduction: 回溯太费时间，找到重复子问题、递推公式。用DP来做
 *
 * p(i,j) = p(i, j-1) + p(i-1, j)
 * 这个是杨辉三角的矩阵版
 * 算法的时间复杂度O(m*n)、空间复杂度为O(m*n)，可优化为O(n)
 *
 * @author sesame 2023/6/15
 */
public class LC062 {

    public int uniquePaths(int m, int n) {
        int[] result = new int[n];

        //初始化第一行
        for (int i = 0; i < n; i++) {
            result[i] = 1;
        }

        //按行填值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j == 1) {
                    result[j] += 1;
                } else {
                    result[j] += result[j - 1];
                }
            }
        }

        return result[n - 1];
    }
}

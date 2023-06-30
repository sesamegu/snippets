package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 * 思路和62题类似，增加一个判断，如果当前为障碍物，则结果为0
 * p(i,j) = p(i, j-1) + p(i-1, j)  if (i,j)不等于0
 * =  0                     if (i,j)等于0
 *
 * @author sesame 2023/6/16
 */
public class LC063 {

    public static void main(String[] args) {

        int[][] data = new int[][] {{0}, {1}};
        final int i = new LC063().uniquePathsWithObstacles(data);
        System.out.println(i);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] result = new int[m][n];

        //初始化第一行、第一列
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                result[0][i] = 0;
            } else {
                if (i == 0) {
                    result[0][i] = 1;
                } else {
                    result[0][i] = result[0][i - 1];
                }
            }
        }
        for (int j = 0; j < m; j++) {
            if (obstacleGrid[j][0] == 1) {
                result[j][0] = 0;
            } else {
                if (j == 0) {
                    result[j][0] = 1;
                } else {
                    result[j][0] = result[j - 1][0];
                }

            }
        }

        //按行填值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    result[i][j] = result[i][j - 1] + result[i - 1][j];
                } else {
                    result[i][j] = 0;
                }
            }
        }

        return result[m - 1][n - 1];
    }
}

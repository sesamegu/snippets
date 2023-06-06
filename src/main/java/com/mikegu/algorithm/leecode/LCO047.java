package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 * 动态规划，递推公式： f(i,j) = max[ f(i-1,j), f(i, j-1)] + grid[i][j]
 *
 * @author sesame 2023/5/7
 */
public class LCO047 {

    public static void main(String[] args) {
        int[][] grid = new int[][] {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };

        final int i = new LCO047().maxValue(grid);

        System.out.println("i = " + i);
    }

    public int maxValue(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;

        int[][] target = new int[row][column];

        //初始化第0行和第0列
        target[0][0] = grid[0][0];
        for (int i = 1; i < column; i++) {
            target[0][i] = target[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            target[i][0] = target[i - 1][0] + grid[i][0];
        }

        //按行填充所有的单元格
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                target[i][j] = Math.max(target[i - 1][j], target[i][j - 1]) + grid[i][j];
            }
        }

        return target[row - 1][column - 1];

    }

}

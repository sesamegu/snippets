package com.mikegu.algorithm.leecode;

import java.util.Arrays;

/**
 * Introduction:  四个方向依次替换，直到所有都被填充；每次碰到边界或者已填充过就换方向
 *
 * @author sesame 2023/6/13
 */
public class LC059 {

    public static void main(String[] args) {
        final int[][] ints = new LC059().generateMatrix(7);
        Arrays.stream(ints).forEach(one ->
            {
                for (int i = 0; i < one.length; i++) {
                    System.out.print(one[i] + " ");
                }
                System.out.println();
            }
        );
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxValue = n * n;
        int row = 0, column = 0;
        int currentValue = 1;
        int dir = 0;
        result[row][column] = currentValue;

        while (currentValue < maxValue) {
            int nextRow = row + directions[dir][0];
            int nextColumn = column + directions[dir][1];

            //是否合法
            if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n) {
                dir = (dir + 1) % 4;
                continue;
            }

            //是否已有值
            if (result[nextRow][nextColumn] != 0) {
                dir = (dir + 1) % 4;
                continue;
            }

            result[nextRow][nextColumn] = ++currentValue ;
            row = nextRow;
            column = nextColumn;
        }

        return result;
    }

}

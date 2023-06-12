package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 *
 * @author sesame 2023/6/10
 */
public class LClongest {

    public static void main(String[] args) {

        //int[][] matrix = new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int[][] matrix = new int[][] {{1}};

        final int i = new LClongest().longestIncreasingPath(matrix);
        System.out.println(i);
    }

    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        //存储每个位置的最大路径值
        int[][] positionLength = new int[row][column];

        //递归计算每个位置
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                calc(matrix, i, j, positionLength);
            }
        }

        //找出最大的值
        int max = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (positionLength[i][j] > max) {
                    max = positionLength[i][j];
                }

            }
        }
        return max;
    }

    private int calc(int[][] matrix, int i, int j, int[][] positionLength) {
        //已计算
        if (positionLength[i][j] != 0) {
            return positionLength[i][j];
        }

        int result = 1;
        int max = 0;
        int current = matrix[i][j];
        //上
        int value = buildRelateValue(matrix, i - 1, j, positionLength, current);
        if (value > max) {
            max = value;
        }
        //下
        value = buildRelateValue(matrix, i + 1, j, positionLength, current);
        if (value > max) {
            max = value;
        }
        //左
        value = buildRelateValue(matrix, i, j - 1, positionLength, current);
        if (value > max) {
            max = value;
        }
        //右
        value = buildRelateValue(matrix, i, j + 1, positionLength, current);
        if (value > max) {
            max = value;
        }

        positionLength[i][j] = result + max;
        return positionLength[i][j];
    }

    private int buildRelateValue(int[][] matrix, int row, int column, int[][] positionLength, int current) {
        //检查范围是否合格
        if (row < 0 || row >= matrix.length) {
            return -1;
        }

        if (column < 0 || column >= matrix[0].length) {
            return -1;
        }

        //比当前值小于，直接返回不可达
        if (matrix[row][column] <= current) {
            return -1;
        }

        //已有值
        if (positionLength[row][column] != 0) {
            return positionLength[row][column];
        }

        return calc(matrix, row, column, positionLength);
    }

}

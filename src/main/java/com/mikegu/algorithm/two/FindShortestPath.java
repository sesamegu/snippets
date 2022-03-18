package com.mikegu.algorithm.two;

/**
 * Introduction:
 *
 * @author sesame 2022/3/18
 */
public class FindShortestPath {


    public static void main(String[] args) {
        int data[][] = new int[][]{{5,}, {7, 8}, {2, 3, 4}, {4, 9, 6, 1}, {2, 7, 9, 4, 5}};

        int number = find(data);
        System.out.println("最短路径为 " + number);
    }

    public static int find(int a[][]) {
        int height = a.length;//高度
        int weight = a.length;//宽度

        int[][] result = new int[height][weight];

        result[0][0] = a[0][0];

        for (int i = 1; i < height; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    result[i][j] = result[i - 1][j] + a[i][j];
                } else if (j == i) {
                    result[i][j] = result[i - 1][j - 1] + a[i][j];
                } else {
                    int left = result[i - 1][j - 1] + a[i][j];
                    int right = result[i - 1][j] + a[i][j];
                    result[i][j] = left < right ? left : right;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < weight; i++) {
            if (result[height - 1][i] < min) {
                min = result[height - 1][i];
            }
        }

        return min;
    }
}

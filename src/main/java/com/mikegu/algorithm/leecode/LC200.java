package com.mikegu.algorithm.leecode;

/**
 * Introduction: 设计一个boolean二维数组，是来表示当前位置是否已访问；
 * 依次遍历每个位置，为0直接结束，为1。岛数增加1，并不断向四面扩展。这里可以用递归
 *
 * 通过存储，每个位置只会处理一次，且处理的方向最多4个。
 * 算法的时间复杂度为O(n*m)，空间复杂度为n*m
 *
 * @author sesame 2023/6/13
 */
public class LC200 {

    public static void main(String[] args) {
        char[][] grid = new char[][] {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        final int i = new LC200().numIslands(grid);
        System.out.println(i);
    }

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        boolean[][] accessed = new boolean[row][column];

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boolean hasIsland = process(grid, i, j, accessed);
                if (hasIsland) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean process(char[][] grid, int i, int j, boolean[][] accessed) {
        //检查位置合法
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }

        //已被访问 或者 位置为0 海水
        if (accessed[i][j] || grid[i][j] == '0') {
            return false;
        }

        accessed[i][j] = true;
        //处理掉相邻的位置
        process(grid, i - 1, j, accessed);
        process(grid, i + 1, j, accessed);
        process(grid, i, j - 1, accessed);
        process(grid, i, j + 1, accessed);

        return true;
    }

}

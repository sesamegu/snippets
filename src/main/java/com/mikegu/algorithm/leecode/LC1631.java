package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 * 剪枝 ： 1，达到这个位置的值 大于等于已知最优解；
 * 2，当前梯度 或者历史梯度  大于等于 当前的位置梯度；
 *
 * 时间复杂度，O(M*N)，因为对于某个节点最多访问4次，因为四个方向 ；
 * 重要：这个判断有问题，因为每个访问四次，这个会叠加的，是指数级别的，所以这个算法设计的有问题，改用官方的算法；
 *
 * 注意点：
 * 访问过的节点不用重复访问；
 * 不要越界
 *
 * @author sesame 2023/7/6
 */
public class LC1631 {

    public static void main(String[] args) {
        int[][] height = {{8, 3, 2, 5, 2, 10, 7, 1, 8, 9}, {1, 4, 9, 1, 10, 2, 4, 10, 3, 5},
            {4, 10, 10, 3, 6, 1, 3, 9, 8, 8}, {4, 4, 6, 10, 10, 10, 2, 10, 8, 8}, {9, 10, 2, 4, 1, 2, 2, 6, 5, 7},
            {2, 9, 2, 6, 1, 4, 7, 6, 10, 9}, {8, 8, 2, 10, 8, 2, 3, 9, 5, 3}, {2, 10, 9, 3, 5, 1, 7, 4, 5, 6},
            {2, 3, 9, 2, 5, 10, 2, 7, 1, 8}, {9, 10, 4, 10, 7, 4, 9, 3, 1, 6}};
        final int i = new LC1631().minimumEffortPath(height);
        System.out.println(i);

    }

    int minGap = Integer.MAX_VALUE;

    public int minimumEffortPath(int[][] heights) {
        minGap = Integer.MAX_VALUE;
        int[][] gaps = new int[heights.length][heights[0].length];
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < gaps.length; i++) {
            for (int j = 0; j < gaps[0].length; j++) {
                gaps[i][j] = Integer.MAX_VALUE;
            }
        }
        //初始化0、0位
        gaps[0][0] = 0;
        dfs(heights, visited, gaps, 0, 0, 0);
        return minGap;
    }

    private void dfs(int[][] heights, boolean[][] visited, int[][] gaps, int row, int column, int currentGap) {
        //System.out.println("row " + row + "\t column  " + column);
        visited[row][column] = true;
        //到达终点结束
        if (row == heights.length - 1 && column == heights[0].length - 1) {
            if (currentGap < minGap) {
                minGap = currentGap;
            }
            visited[row][column] = false;
            return;
        }

        //向上
        visited(heights, visited, gaps, row, column, currentGap, row - 1, column);
        //向下
        visited(heights, visited, gaps, row, column, currentGap, row + 1, column);
        //向左
        visited(heights, visited, gaps, row, column, currentGap, row, column - 1);
        //向右
        visited(heights, visited, gaps, row, column, currentGap, row, column + 1);

    }

    private void visited(int[][] heights, boolean[][] visited, int[][] gaps, int row, int column, int currentGap,
        int nextRow, int nextColumn) {
        if (nextRow >= 0 && nextRow < heights.length && nextColumn >= 0 && nextColumn < heights[0].length) {
            //已访问过的节点不用再访问
            if (visited[nextRow][nextColumn]) {
                return;
            }

            final int gap = Math.abs(heights[row][column] - heights[nextRow][nextColumn]);
            if (gap < minGap && gap < gaps[nextRow][nextColumn] && currentGap < gaps[nextRow][nextColumn]) {
                final int max = Math.max(currentGap, gap);
                gaps[nextRow][nextColumn] = max;
                dfs(heights, visited, gaps, nextRow, nextColumn, max);
                visited[nextRow][nextColumn] = false;
            }
        }
    }

}

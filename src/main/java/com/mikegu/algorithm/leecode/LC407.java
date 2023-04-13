package com.mikegu.algorithm.leecode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Introduction:
 * 时间复杂度：O(MNlog(MN))
 *
 * 首先我们思考一下什么样的方块一定可以接住水：
 * 该方块不为最外层的方块；
 * 该方块自身的高度比其上下左右四个相邻的方块接水后的高度都要低；
 *
 * 接水后的高度
 * water[i][j]=max(heightMap[i][j],min(water[i−1][j],water[i+1][j],water[i][j−1],water[i][j+1]))
 *
 * 从外围的最短板开始，只要是低的就是低洼
 *
 * @author sesame 2023/4/13
 */
public class LC407 {

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        //优先级队列保证log(n)的性能
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        //初始化四边界
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int res = 0;
        //四个方向
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            for (int k = 0; k < 4; ++k) {
                int nx = curr[0] / n + dirs[k];
                int ny = curr[0] % n + dirs[k + 1];
                //不越界且没有访问过
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    //存在低洼
                    if (curr[1] > heightMap[nx][ny]) {
                        res += curr[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], curr[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }

}

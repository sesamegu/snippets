package com.mikegu.algorithm.leecode;

/**
 * Introduction: 用动态规划即可，对应的转移方程
 *    dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]}
 *    dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
 *    自己也想到了，思路一致
 * @author sesame 2023/4/16
 */
public class LC122 {



    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

}

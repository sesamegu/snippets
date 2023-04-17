package com.mikegu.algorithm.leecode;

/**
 * Introduction: 这题没想到。官方的答案比较难理解，下面是网友的解决，思路很清晰
 *
 * @author sesame 2023/4/16
 */
public class LC123 {

    public int maxProfit(int[] prices) {
        int max = 0;
        int minVal = prices[0];
        //从低到高，dp[i]表示第i天以及之前的区间所获得的最大利润
        int[] dp = new int[prices.length];
        //从高到低，dp2[i]表示第i天开始直到最后一天期间所获得的最大利润
        int[] dp2 = new int[prices.length];
        dp[0] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            dp[i] = Math.max(dp[i - 1], prices[i] - minVal);
            minVal = Math.min(prices[i], minVal);
        }

        int maxVal = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; --i) {
            dp2[i] = Math.max(dp2[i + 1], maxVal - prices[i]);
            maxVal = Math.max(maxVal, prices[i]);
        }

        for (int i = 1; i <= prices.length - 1; ++i) {
            max = Math.max(dp[i - 1] + dp2[i], max);
        }
        return Math.max(dp[prices.length - 1], max);

    }
}

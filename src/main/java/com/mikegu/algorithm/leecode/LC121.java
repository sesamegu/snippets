package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 *
 * @author sesame 2023/4/16
 */
public class LC121 {

    // 时间复杂度O(n)，记录下最低价格，不断前行即可
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int max = 0;
        int low = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < low) {
                low = prices[i];
            } else {
                if (prices[i] - low > max) {
                    max = prices[i] - low;
                }
            }
        }
        return max;
    }
}

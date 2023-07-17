package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 *
 * @author sesame 2023/7/6
 */
public class LC1423 {

    public static void main(String[] args) {
        final int[] cardPoints = new int[] {1,2,3,4,5,6,1};
        System.out.println(new LC1423().maxScore(cardPoints, 3));

    }

    public int maxScore(int[] cardPoints, int k) {
        //把K张牌拆分为前i张和后k-i张，求最大值，总共K+1种情况
        int n = cardPoints.length;
        //需要的张数大于等于 输入的牌数
        if (k >= n) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += cardPoints[i];
            }
            return sum;
        }

        //初始化第一个计算
        int current = 0;
        for (int i = 0; i < k; i++) {
            current += cardPoints[i];
        }


        int max = current;
        for (int prefix = k - 1; prefix >= 0; prefix--) {
            int suffix = k - prefix;
            //优化为每次变换两个数
            int next = current - cardPoints[prefix] + cardPoints[n - suffix];
            current = next;

            if (current > max) {
                max = current;
            }

        }

        return max;
    }

}

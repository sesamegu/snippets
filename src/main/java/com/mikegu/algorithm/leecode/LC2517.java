package com.mikegu.algorithm.leecode;

import java.util.Arrays;

/**
 * Introduction:
 *
 * @author sesame 2023/7/6
 */
public class LC2517 {

    public int maximumTastiness(int[] price, int k) {
        //排序
        Arrays.sort(price);
        //可能的范围值
        int left = 0, right = price[price.length - 1] - price[0];
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (check(price, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //贪心算法检查满足条件的最大值
    public boolean check(int[] price, int k, int tastiness) {
        int prev = Integer.MIN_VALUE / 2;
        int cnt = 0;
        for (int p : price) {
            if (p - prev >= tastiness) {
                cnt++;
                prev = p;
            }
        }
        return cnt >= k;
    }

}

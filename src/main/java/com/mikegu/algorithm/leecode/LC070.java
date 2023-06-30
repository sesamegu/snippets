package com.mikegu.algorithm.leecode;

/**
 * Introduction: dp(n) = dp(n-1) + dp(n-2)
 *
 * @author sesame 2023/6/16
 */
public class LC070 {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int step = 2;
        int result = 1;
        int current = 1;
        int next = 2;

        while (step < n) {
            result = current + next;
            current = next;
            next = result;
            step++;
        }

        return result;
    }
}

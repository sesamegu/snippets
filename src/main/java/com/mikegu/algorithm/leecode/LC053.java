package com.mikegu.algorithm.leecode;

/**
 * Introduction: 依次向前查找，发现累计为负则开始重新计算。因为为负后，后面的字串肯定是不包含负数的值更大；时间复杂度 O(n)
 *
 * @author sesame 2023/6/12
 */
public class LC053 {

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; ) {
            int current = nums[i];
            if (current > max) {
                max = current;
            }
            while (current > 0 && i < nums.length - 1) {
                i++;
                current += nums[i];
                if (current > max) {
                    max = current;
                }

            }

            i++;
        }

        return max;
    }
}


package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 *
 * @author sesame 2023/7/14
 */
public class LC213 {

    public static void main(String[] args) {
        final int rob = new LC213().rob(new int[] {1, 2, 3,});
        System.out.println(rob);
    }

    public int rob(int[] nums) {
        // 一个元素
        if (nums.length == 1) {
            return nums[0];
        }
        // 两个元素
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }

        int max = 0;
        //打劫第一个家，不打劫最后一个家
        int[] result = new int[nums.length];
        result[0] = nums[0];
        result[1] = nums[0];
        for (int i = 2; i < nums.length - 1; i++) {
            int left = result[i - 2] + nums[i];
            int right = result[i - 1];
            result[i] = left > right ? left : right;
        }
        max = result[nums.length - 2];

        //不打劫第一个家，最后一个家可以打劫，也不可以不打劫
        result[0] = 0;
        result[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int left = result[i - 2] + nums[i];
            int right = result[i - 1];

            result[i] = left > right ? left : right;
        }
        if (max < result[nums.length - 1]) {
            max = result[nums.length - 1];
        }

        return max;
    }
}

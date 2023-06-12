package com.mikegu.algorithm.leecode;

/**
 * Introduction: 递推公式 money[i] = max( money[i-2]+data[i], money[i-1])
 * 存储下结果，避免重复计算
 *
 * @author sesame 2023/6/7
 */
public class LCrob {

    public static void main(String[] args) {

        int[] nums = new int[] {1,2,3,1};
        final int rob = new LCrob().rob(nums);
        System.out.println(rob);

    }

    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        int[] result = new int[nums.length];
        result[0] = nums[0];
        result[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        if (nums.length == 2) {
            return result[1];
        }

        for (int i = 2; i < nums.length; i++) {
            int left = result[i - 2] + nums[i];
            int right = result[i - 1];

            result[i] = left > right ? left : right;

        }

        return result[nums.length - 1];
    }
}

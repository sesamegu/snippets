package com.mikegu.algorithm.leecode;

/**
 * Introduction: data[i] = max( data[0].....data[i-1] )
 *
 * @author sesame 2023/6/12
 */
public class LC055 {

    public static void main(String[] args) {
        int[] nums = new int[] {3,2,1,0,4};
        final boolean b = new LC055().canJump(nums);
        System.out.println(b);
    }

    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }

        int max = nums[0];
        //已达
        if (max >= nums.length - 1) {
            return true;
        }

        //最后一位不用计算
        for (int i = 1; i < nums.length - 1 && i <= max; i++) {
            int current = nums[i] + i;
            if (current > max) {
                max = current;
            }

            if (max >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }
}

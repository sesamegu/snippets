package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 *
 * @author sesame 2023/4/14
 */
public class LC041 {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1};
        final int result = new LC041().firstMissingPositive(nums);
        System.out.println(result);
    }

    public int firstMissingPositive(int[] nums) {

        if (nums.length == 0) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            //超过最大值 或者小于等于 0 做无效数字处理
            if (current > nums.length || current <= 0) {
                nums[i] = 0;
            } else {
                if (current == i + 1) {
                    continue;
                } else {
                    int nextValue = nums[current - 1];
                    nums[current - 1] = current;
                    nums[i] = 0;
                    //这里全局最多n次
                    while (nextValue > 0 && nextValue <= nums.length && nextValue != nums[nextValue - 1]) {
                        current = nextValue;
                        nextValue = nums[current - 1];
                        nums[current - 1] = current;
                    }

                }

            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i + 1) {
                continue;
            } else {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}

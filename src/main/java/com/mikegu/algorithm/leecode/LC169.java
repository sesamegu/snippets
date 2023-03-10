package com.mikegu.algorithm.leecode;

/**
 * https://leetcode.cn/problems/majority-element/
 * Introduction:进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * 空间度为1 需要特殊的逻辑操作，
 *
 * @author sesame 2023/3/9
 */
public class LC169 {
    public static void main(String[] args) {
        int[] number = new int[] {2, 2, 1, 1, 1, 2, 2};

        System.out.println(new LC169().majorityElement(number));
    }

    public int majorityElement(int[] nums) {
        int major = nums[0];
        int start = 1;
        int count = 1;
        while (start < nums.length) {

            if (major != nums[start]) {
                count--;
                if (count == 0) {
                    count = 1;
                    major = nums[start + 1];
                    start = start + 2;
                    continue;
                }
            } else {
                count++;
            }

            start = start + 1;

        }

        return major;
    }
}

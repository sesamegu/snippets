package com.mikegu.algorithm.leecode;

/**
 * Introduction: 我用了dp的思想，合理化剪枝；
 * 官方的第二解法巧妙更好，用greedy思想，计算出每个位置能到达的最大位置，每次超过最大位置加1。指直到到达最后。
 * @author sesame 2023/4/15
 */
public class LC045 {

    public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 1, 1, 4};
        final int jump = new LC045().jump(nums);
        System.out.println(jump);
    }

    ////step存储的是到当前位置的最小步数，合理化剪枝
    //public int jump(int[] nums) {
    //    if (nums.length == 0) {
    //        return -1;
    //    }
    //    //初始化
    //    int[] steps = new int[nums.length];
    //    for (int i = 0; i < steps.length; i++) {
    //        steps[i] = 999999;
    //    }
    //    steps[0] = 0;
    //
    //    for (int i = 0; i < steps.length; i++) {
    //        int avaiStep = nums[i];
    //        //不能达到的位置
    //        if (avaiStep == 999999) {
    //            continue;
    //        }
    //        //遍历所有的可能
    //        for (int j = 1; j <= avaiStep && i + j < nums.length; j++) {
    //            if (steps[i + j] > steps[i] + 1) {
    //                steps[i + j] = steps[i] + 1;
    //            }
    //        }
    //    }
    //
    //    return steps[steps.length - 1];
    //
    //}
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}

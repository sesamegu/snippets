package com.mikegu.algorithm.leecode;

/**
 * Introduction: 题目本来是很简答的，加了一个限定条件 "只使用常量额外空间"，导致一般的解法都无效。
 *
 * @author sesame 2023/3/9
 */
public class LC136 {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int one : nums) {
            result ^= one;
        }
        return result;
    }

}

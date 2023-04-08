package com.mikegu.algorithm.leecode;

import java.util.HashMap;
import java.util.Objects;

/**
 * Introduction: 通过Hash实现快速查找，达到O(n)的时间
 *
 * @author sesame 2023/3/12
 */
public class LC001 {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            Integer index = map.get(remain);
            if (Objects.isNull(index) || i == index) {
                continue;
            }

            return new int[] {i, index};
        }

        return null;
    }
}

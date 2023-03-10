package com.mikegu.algorithm.leecode;

/**
 * Introduction: 双指针。一边高、一边低，只有移动低的一边才能找到更大值。
 *
 * @author sesame 2023/3/9
 */
public class LC011 {

    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;

        int max = 0;
        while (start < end) {
            int area;
            if (height[start] >= height[end]) {
                area = height[end] * (end - start);
                end = end - 1;
            } else {
                area = height[start] * (end - start);
                start = start + 1;
            }
            if (area > max) {
                max = area;
            }
        }

        return max;
    }
}

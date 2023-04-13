package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduction: 我想的办法类似官方的 用单调栈实现，时间复杂度为o(n)；不过这个办法不直接，不如第一个"动态规划"法，
 * 第三个双指针是在第一个方法"动态规划"的基础上，节省了空间。
 * 方案1，3都基本思路：对于下标i下雨后水能到达的最大高度，等于下标 i 两边的最大高度的最小值 减去 height[i]
 * 在计算两边最大高度时:
 * 方案1动态规划 使用 leftMax[i]=max(leftMax[i−1],height[i])， rightMax[i]=max(rightMax[i+1],height[i]) 预计算
 * 方案3动态规划双指针 使用双指针和两个变量代替两个数组，left<right时 指针left向右移动，left>right时 指针right只会向左移动
 *
 *
 * @author sesame 2023/4/11
 */
public class LC042 {


    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }


    //private int index = 0;
    //
    //public int trap(int[] height) {
    //    if (height.length < 3) {
    //        return 0;
    //    }
    //
    //    //存储所有的池子
    //    List<Pool> poolList = new ArrayList<>();
    //
    //    while (index < height.length) {
    //
    //        //寻找start
    //        int start = -1;
    //        int low = -1;
    //        while (index + 2 < height.length) {
    //            if (height[index] > height[index + 1]) {
    //                start = index;
    //                low = index + 1;
    //                break;
    //            } else if (height[index] == height[index + 1]) {
    //                index++;
    //            } else {
    //                for (int i = poolList.size() - 1; i >= 0; i++) {
    //
    //
    //                }
    //
    //                index++;
    //            }
    //        }
    //
    //        if (index + 2 == height.length) {
    //            break;
    //        }
    //
    //        //寻找end
    //        index += 2;
    //        while (index < height.length) {
    //            if (height[index] <= height[low]) {
    //                low = index;
    //                index++;
    //                continue;
    //            } else {
    //                //末尾大于等于起始值，这个池子不会收到后面的数字影响，直接固定
    //                // 否则，这个池子有可能扩大
    //                if (height[index] >= height[start]) {
    //                    poolList.add(new Pool(start, low, index, true));
    //                } else {
    //                    poolList.add(new Pool(start, low, index, false));
    //                }
    //            }
    //
    //        }
    //
    //    }
    //
    //    //todo
    //    return 0;
    //}
    //
    //class Pool {
    //    int start;
    //    int low;
    //    int end;
    //    boolean freezed;
    //
    //    public Pool(int start, int low, int end, boolean freezed) {
    //        this.start = start;
    //        this.low = low;
    //        this.end = end;
    //        this.freezed = freezed;
    //    }
    //}

}

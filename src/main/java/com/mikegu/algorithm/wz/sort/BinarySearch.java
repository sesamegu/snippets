package com.mikegu.algorithm.wz.sort;

import org.springframework.util.Assert;

/**
 * Introduction:
 * 我们今天讲的都是非常规的二分查找问题，今天的思考题也是一个非常规的二分查找问题。如果有序数组是一个循环有序数组，
 * 比如4，5，6，1，2，3。针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢？
 *
 * 思路：
 * 通过找到比 第一个数小 的第一个数【二分查找】，来界定边界；
 * 在两个数组中，亦需要 二分查找
 *
 * @author sesame 2022/5/22
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] data = new int[] {4, 5, 6, 8, 9, 1, 2, 3};
        int given = 4;

        // 通过找到比 第一个数小 的第一个数【二分查找】，来界定边界
        int pivot = findThePivot(data);
        System.out.println("pivot = " + pivot);

        if (given >= data[0] && given <= data[pivot - 1]) {
            int result = binarySearch(data, 0, pivot - 1, given);
            System.out.println("result = " + result);
            return;
        }

        if (given >= data[pivot] && given <= data[data.length - 1]) {
            int result = binarySearch(data, pivot, data.length - 1, given);
            System.out.println("result = " + result);
        } else {
            System.out.println("result  = -1");
            return;
        }

    }

    private static int binarySearch(int[] data, int start, int end, int given) {
        int left = start;
        int right = end;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (data[middle] == given) {
                return middle;
            } else if (data[middle] > given) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

    private static int findThePivot(int[] data) {
        Assert.isTrue(data != null && data.length > 2, "illegal data");

        int left = 1;
        int right = data.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);

            if (data[middle] > data[0]) {
                left = middle + 1;
            } else {
                // 不考虑数据中存在相同情况
                if (middle == 2 || data[middle - 1] >= data[0]) {
                    return middle;
                }
                right = middle;
            }

        }

        System.err.println("left = " + left + "\t right = " + right);
        throw new RuntimeException("shouldn't be here");
    }

}

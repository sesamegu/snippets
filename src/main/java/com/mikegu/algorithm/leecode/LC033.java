package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 *
 * @author sesame 2023/3/15
 */
public class LC033 {
    public static void main(String[] args) {

        int[] nums = new int[] {3,1};
        System.out.println(new LC033().search(nums, 1));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        //确定分界线
        int theFirst = nums[0];

        int low = 0;
        int high = nums.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= theFirst) {
                low = mid + 1;
            } else {
                if (nums[mid - 1] >= theFirst) {
                    index = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            }
        }

        // 确定查找的开始和结束点
        int startPoint;
        int endPoint;
        if (index == -1) {
            startPoint = 0;
            endPoint = nums.length - 1;
        } else {
            if (theFirst <= target) {
                startPoint = 0;
                endPoint = index - 1;
            } else {
                startPoint = index;
                endPoint = nums.length - 1;
            }

        }

        //标准二分查找
        while (startPoint <= endPoint) {
            int middle = startPoint + (endPoint - startPoint) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                startPoint = middle + 1;
            } else {
                endPoint = middle - 1;
            }
        }

        return -1;
    }
}

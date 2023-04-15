package com.mikegu.algorithm.leecode;

/**
 * Introduction:
 *
 * @author sesame 2023/4/15
 */
public class LC034 {

    public static void main(String[] args) {
        int[] nm = new int[] {2, 2};
        final int[] ints = new LC034().searchRange(nm, 2);
        System.out.println(ints[0] + "\t" + ints[1]);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] notFound = new int[] {-1, -1};
        if (nums.length == 0) {
            return notFound;
        }
        int low = 0;
        int high = nums.length - 1;

        int index = binSearch(nums, target, low, high);
        if (index == -1) {
            return notFound;
        }

        int[] result = new int[2];
        result[0] = binSearchSmall(nums, target, low, index);
        result[1] = binSearchBig(nums, target, index, high);

        return result;
    }

    private int binSearchSmall(int[] nums, int target, int low, int high) {
        while (low <= high) {
            int mid = ((high - low) >> 1) + low;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }

                high = mid - 1;
            } else {
                high = mid - 1;
            }
        }

        throw new RuntimeException("binSearchSmall");
    }

    private int binSearchBig(int[] nums, int target, int low, int high) {
        while (low <= high) {
            int mid = ((high - low) >> 1) + low;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] == target) {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                }

                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        throw new RuntimeException("binSearchBig");
    }

    private int binSearch(int[] nums, int target, int low, int high) {
        //二分找到或者返回
        while (low <= high) {
            int mid = ((high - low) >> 1) + low;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return -1;
    }
}

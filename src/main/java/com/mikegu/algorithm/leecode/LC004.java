package com.mikegu.algorithm.leecode;

/**
 * Introduction: 递归求解，每次从数组中获取需要的数字的一半多 （nums1, nums2, 0, 0, number）
 *
 * @author sesame 2023/4/6
 */
public class LC004 {

    public static void main(String[] args) {
        int[] nums1 = new int[] {1};
        int[] nums2 = new int[] {2, 3, 4};
        double result = new LC004().findMedianSortedArrays(nums1, nums2);
        System.out.println("result = " + result);

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] target = null;

        //有一个数组为空
        if (nums1 == null || nums1.length == 0) {
            target = nums2;
        }
        if (nums2 == null || nums2.length == 0) {
            target = nums1;
        }

        if (target != null) {
            final int length = target.length;
            if (length % 2 == 0) {
                return (target[length / 2 - 1] + target[length / 2]) / 2.0;
            } else {
                return target[length / 2];
            }
        }

        boolean isEven = ((nums1.length + nums2.length) % 2 == 0);

        if (isEven) {
            //偶数直接简单处理了做两次，这里可优化为一次获得
            int number = (nums1.length + nums2.length) / 2;
            int a = find(nums1, nums2, 0, 0, number);
            number = (nums1.length + nums2.length) / 2 + 1;
            int b = find(nums1, nums2, 0, 0, number);
            return (a + b) / 2.0;
        } else {
            int number = (nums1.length + nums2.length) / 2 + 1;
            return find(nums1, nums2, 0, 0, number);
        }

    }

    private int find(int[] firstNums, int[] secondNums, int nNext, int mNext, int number) {
        if (number == 0) {
            if (nNext == 0) {
                return secondNums[mNext - 1];
            }
            if (mNext == 0) {
                return firstNums[nNext - 1];
            }
            return firstNums[nNext - 1] > secondNums[mNext - 1] ? firstNums[nNext - 1] : secondNums[mNext - 1];
        }

        if (number == 1) {
            //n数组已用完
            if (nNext >= firstNums.length) {
                return secondNums[mNext];
            }
            //m数组已用完
            if (mNext >= secondNums.length) {
                return firstNums[nNext];
            }
            //取小的那个
            return firstNums[nNext] > secondNums[mNext] ? secondNums[mNext] : firstNums[nNext];
        }

        //如果一个数组已取完
        if (nNext == firstNums.length) {
            int index = mNext + number - 1;
            return secondNums[index];
        }
        if (mNext == secondNums.length) {
            int index = nNext + number - 1;
            return firstNums[index];
        }

        //从n、m数组中尝试各取一半
        int firstIndex = nNext + number / 2 - 1;
        int secondIndex = mNext + number / 2 - 1;
        if (firstIndex >= firstNums.length) {
            firstIndex = firstNums.length - 1;
        }
        if (secondIndex >= secondNums.length) {
            secondIndex = secondNums.length - 1;
        }

        //通过比较确定边界
        if (firstNums[firstIndex] >= secondNums[secondIndex]) {
            int bs = binarySearch(firstNums, nNext, firstIndex, secondNums[secondIndex]);
            if (bs == -1) {
                firstIndex = nNext;
            } else {
                firstIndex = bs;
            }
            secondIndex += 1;
        } else {
            int bs = binarySearch(secondNums, mNext, secondIndex, firstNums[firstIndex]);
            if (bs == -1) {
                secondIndex = mNext;
            } else {
                secondIndex = bs;
            }
            firstIndex += 1;
        }

        int left = number - (firstIndex - nNext) - (secondIndex - mNext);
        return find(firstNums, secondNums, firstIndex, secondIndex, left);
    }

    /**
     * 找到小于given的最大位置 的下一个位置
     */
    private int binarySearch(int[] data, int start, int end, int given) {
        int left = start;
        int right = end;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (data[middle] >= given) {
                right = middle - 1;
            } else {
                if (left + 1 > end || data[left + 1] >= given) {
                    return left + 1;
                }
                left = middle + 1;
            }
        }

        return -1;
    }

}

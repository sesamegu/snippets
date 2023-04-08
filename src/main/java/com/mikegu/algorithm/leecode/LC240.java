package com.mikegu.algorithm.leecode;

/**
 * Introduction: 官方题解，最优化的方案O(n+m)，这个没有想到。一致以为能找到O(log n）的算法
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 *
 * @author sesame 2023/3/15
 */
public class LC240 {
    public static void main(String[] args) {
        //
        //int[][] matrix = new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24},
        //    {18, 21, 23, 26, 30}};

        int[][] matrix = new int[][] {{1,3,5,7,9},{2,4,6,8,10},{11,13,15,17,19},{12,14,16,18,20},{21,22,23,24,25}};
        System.out.println(new LC240().searchMatrix(matrix, 13));
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

    //public boolean searchMatrix(int[][] matrix, int target) {
    //
    //    //搜索第一行
    //    int[] firstRow = new int[matrix[0].length];
    //    for (int i = 0; i < matrix[0].length; i++) {
    //        firstRow[i] = matrix[0][i];
    //    }
    //    Integer columnIndex = findEqualOrLess(firstRow, target);
    //    if (columnIndex == null) {
    //        return true;
    //    }
    //
    //    //搜索第n列, 如果为-1则代表不用检查
    //    if (columnIndex != -1) {
    //        int[] nums = new int[matrix.length];
    //        for (int i = 0; i < matrix.length; i++) {
    //            nums[i] = matrix[i][columnIndex];
    //        }
    //        final Integer equalOrLessRow = findEqualOrLess(nums, target);
    //        if (equalOrLessRow==null) {
    //            return true;
    //        }
    //
    //        if (equalOrLessRow != -1){
    //
    //        }
    //    }
    //
    //    //搜索第一列
    //    int[] firstColumn = new int[matrix.length];
    //    for (int i = 0; i < matrix.length; i++) {
    //        firstColumn[i] = matrix[i][0];
    //    }
    //    Integer rowIndex = findEqualOrLess(firstColumn, target);
    //    if (rowIndex == null) {
    //        return true;
    //    }
    //
    //    //搜索第n行, 如果为-1则代表不用检查
    //    if (rowIndex != -1) {
    //        int[] nums = buildRow(matrix, matrix[rowIndex]);
    //        if (binarySearch(nums, target)) {
    //            return true;
    //        }
    //    }
    //
    //    return false;
    //}
    //
    //private int[] buildRow(int[][] matrix, int[] matrix1) {
    //    int[] nums = new int[matrix[0].length];
    //    for (int i = 0; i < matrix[0].length; i++) {
    //        nums[i] = matrix1[i];
    //    }
    //    return nums;
    //}
    //
    //private Integer findEqualOrLess(int[] nums, int target) {
    //    int low = 0;
    //    int high = nums.length - 1;
    //    int columnIndex = -1;
    //    while (low <= high) {
    //        int middle = low + (high - low) / 2;
    //        if (nums[middle] == target) {
    //            return null;
    //        } else if (nums[middle] < target) {
    //            // 下一个数不是最后一个数且大于目标值  或者下一个数就是最后一个数
    //            int nextIndex = middle + 1;
    //            boolean nextExist = nextIndex < nums.length;
    //            if ((nextExist && nums[nextIndex] > target) || !nextExist) {
    //                columnIndex = middle;
    //                break;
    //            }
    //            low = middle + 1;
    //        } else {
    //            high = middle - 1;
    //        }
    //    }
    //    return columnIndex;
    //}
    //
    //public boolean binarySearch(int[] nums, int target) {
    //    // 确定查找的开始和结束点
    //    int low = 0;
    //    int high = nums.length - 1;
    //
    //    //标准二分查找
    //    while (low <= high) {
    //        int middle = low + (high - low) / 2;
    //        if (nums[middle] == target) {
    //            return true;
    //        } else if (nums[middle] < target) {
    //            low = middle + 1;
    //        } else {
    //            high = middle - 1;
    //        }
    //    }
    //
    //    return false;
    //}
}

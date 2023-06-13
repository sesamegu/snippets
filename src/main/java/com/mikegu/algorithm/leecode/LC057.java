package com.mikegu.algorithm.leecode;

import java.util.Arrays;

/**
 * Introduction:  二分查找确定新区间的位置，然后根据不同情况合并
 * 改为二分查找性能提升有限，原因在于，输出是需要遍历整个数组
 * O(n)
 *
 * @author sesame 2023/6/13
 */
public class LC057 {

    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1, 5}, {6, 8}, {9, 10}};
        int[] newInterval = new int[] {6, 8};
        final int[][] insert = new LC057().insert(intervals, newInterval);
        Arrays.stream(insert).forEach(one -> System.out.println(one[0] + " " + one[1]));
        //
        //int value = 7;
        ////final OneResult findR = new LC057().find(intervals, value);
        //final OneResult binR = new LC057().binarySearch(intervals, value);
        //final boolean b = findR.between == binR.between;
        //final boolean b2 = findR.index == binR.index;
        //
        //System.out.println(b + " \t" + b2);

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] t = new int[1][2];
            t[0][0] = newInterval[0];
            t[0][1] = newInterval[1];
            return t;
        }

        // start
        OneResult start = binarySearch(intervals, newInterval[0]);
        OneResult end = binarySearch(intervals, newInterval[1]);

        //delete
        int deleteStart = -1;
        int deleteEnd = -1;

        //都在区间
        if (start.between && end.between) {
            if (start.index == end.index) {
                //无需改变
            } else {
                intervals[start.index][1] = intervals[end.index][1];
                deleteStart = start.index + 1;
                deleteEnd = end.index;
            }
        } else if (start.between) {
            intervals[start.index][1] = newInterval[1];
            if (end.index >= start.index + 1) {
                deleteStart = start.index + 1;
                deleteEnd = end.index - 1;
            }
        } else if (end.between) {
            intervals[end.index][0] = newInterval[0];
            if (start.index < end.index) {
                deleteStart = start.index;
                deleteEnd = end.index - 1;
            }
        } else {
            if (start.index == end.index) {
                //需要插入
                int[][] result = new int[intervals.length + 1][2];
                for (int i = 0, j = 0; i < intervals.length; i++) {
                    if (i == end.index) {
                        result[j][0] = newInterval[0];
                        result[j][1] = newInterval[1];
                        j++;
                    }
                    result[j][0] = intervals[i][0];
                    result[j][1] = intervals[i][1];
                    j++;
                }
                if (end.index == intervals.length) {
                    result[intervals.length][0] = newInterval[0];
                    result[intervals.length][1] = newInterval[1];
                }

                return result;
            } else {
                intervals[start.index][0] = newInterval[0];
                intervals[start.index][1] = newInterval[1];
                if (start.index + 1 < end.index) {
                    deleteStart = start.index + 1;
                    deleteEnd = end.index - 1;
                }
            }
        }

        int gap = 0;
        //处理删除位置
        if (deleteStart != -1) {
            gap = deleteEnd - deleteStart + 1;
        }
        int[][] result = new int[intervals.length - gap][2];
        for (int i = 0, j = 0; i < intervals.length; i++) {
            if (i < deleteStart || i > deleteEnd) {
                result[j][0] = intervals[i][0];
                result[j][1] = intervals[i][1];
                j++;
            }
        }
        return result;
    }

    //protected OneResult find(int[][] intervals, int t) {
    //    //todo 改成二分查找
    //    for (int i = 0; i < intervals.length; i++) {
    //        if (t < intervals[i][0]) {
    //            return new OneResult(false, i);
    //        } else if (t >= intervals[i][0] && t <= intervals[i][1]) {
    //            return new OneResult(true, i);
    //        }
    //    }
    //
    //    return new OneResult(false, intervals.length);
    //}

    protected OneResult binarySearch(int[][] intervals, int t) {
        int low = 0;
        int high = intervals.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (intervals[middle][0] > t) {
                if (middle == 0 || intervals[middle - 1][1] < t) {
                    return new OneResult(false, middle);
                }
                high = middle - 1;
            } else if (intervals[middle][1] < t) {
                if (middle == intervals.length - 1 || intervals[middle + 1][0] > t) {
                    return new OneResult(false, middle + 1);
                }

                low = middle + 1;
            } else {
                return new OneResult(true, middle);
            }

        }

        return null;
    }

    class OneResult {
        boolean between;
        int index;

        public OneResult(boolean between, int index) {
            this.between = between;
            this.index = index;
        }
    }

}

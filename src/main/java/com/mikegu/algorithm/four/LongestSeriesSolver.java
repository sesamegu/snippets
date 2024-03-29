package com.mikegu.algorithm.four;

import lombok.Data;

/**
 * 动态规划：
 * f(n) 代表包含n的最大长度
 * t[f(n-1),n] 代表是否连续 ？f(n-1) + 1 : 0
 *
 * 最大长度 =  max[f(n),f(n-1),f(n-2)......f(1)]
 * f(n) = max {t[f(n-1),n],  t[f(n-2),n] ...... t[f(1),n], 1}
 *
 * @author sesame 2022/3/29
 */
public class LongestSeriesSolver {

    public static void main(String[] args) {
        int[] data = new int[] {
            2, 9, 3, 6, 5, 1, 7, 6, 7, 8, 10, 11, 23
        };

        int result = findTheLongest(data);
        System.out.println("The longest length is " + result);

    }

    private static int findTheLongest(int[] data) {
        Range[] bestRanges = new Range[data.length];

        // 初始第一个
        bestRanges[0] = new Range(0, 0, 1);

        // 循环增加一个数，找到当前潜在的最大长度
        for (int i = 1; i < data.length; i++) {
            //找到包含当前位置的最大的长度。初始化最大值为开始和结束为当前位置，长度为1
            Range max = new Range(i, i, 1);
            for (int j = 0; j < i; j++) {
                //无法连接
                if (data[bestRanges[j].getEnd()] >= data[i]) {
                    continue;
                } else { //可以连接
                    Range range = new Range(bestRanges[j].getStart(), i, bestRanges[j].getLength() + 1);
                    if (range.getLength() > max.getLength()) {
                        max = range;
                    }
                }
            }
            bestRanges[i] = max;
        }

        // 找到最长的那个
        int max = Integer.MIN_VALUE;
        for (Range range : bestRanges) {
            System.out.println("开始位置:" + range.getStart() + "\t结束位置:" + range.getEnd() + "\t 长度:" + range.getLength());
            if (range.getLength() > max) {
                max = range.getLength();
            }
        }
        return max;
    }

}

@Data
class Range {
    /**
     * 开始位置
     */
    private int start;
    /**
     * 结束位置
     */
    private int end;
    /**
     * 包含开始和结束位置，最大长度
     */
    private int length;

    public Range(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }
}

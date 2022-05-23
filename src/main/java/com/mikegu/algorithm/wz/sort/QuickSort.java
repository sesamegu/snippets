package com.mikegu.algorithm.wz.sort;

import java.util.Arrays;

/**
 * Introduction: 快排
 *
 * @author sesame 2022/4/19
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] original = new int[] {
            1, 25, 6, 9, 11, 33, 34, 5, 2, 4, 4, 5, 7, 8, 10, 222, 23, 343, 33, 41, 2, 3, 454, 2, 3
        };

        quickSort(original, 0, original.length - 1);
        Arrays.stream(original).forEach(
            one -> System.out.print(one + "\t")
        );

    }

    private static void quickSort(int[] data, int start, int end) {
        //只剩一个元素
        if (start >= end) {
            return;
        }

        //选择第一个做为pivot
        int pivot = data[start];

        int i = start + 1;
        int j = end;

        while (i <= j) {
            if (data[i] <= pivot) {
                i++;
                continue;
            }

            if (data[j] > pivot) {
                j--;
                continue;
            }

            int swap = data[j];
            data[j] = data[i];
            data[i] = swap;
            i++;
            j--;
        }
        //交换pivot和最后一个小于的位置，如果存在
        if (i != start + 1) {
            data[start] = data[i - 1];
            data[i - 1] = pivot;
        }

        quickSort(data, start, i - 2);
        quickSort(data, i, end);
    }

}

package com.mikegu.algorithm.leecode;

/**
 * Introduction: 时间复杂度为o(n)，关键是跳跃逻辑：从i开始k不可行，那么直接从k开始，因为从i到i+1,i+2.... k-1都是有正数。
 * 所以从i+1,i+2.... k-1开始到k都是不可行的。
 *
 * @author sesame 2023/6/6
 */
public class LCGas {

    public static void main(String[] args) {

        //int[] gas = new int[] {1, 2, 3, 4, 5};
        //int[] cost = new int[] {3, 4, 5, 1, 2};

        int[] gas = new int[] {2, 3, 4};
        int[] cost = new int[] {3, 4, 3};

        final int i = new LCGas().canCompleteCircuit(gas, cost);
        System.out.println(i);

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 1) {
            return gas[0] >= cost[0] ? 0 : -1;
        }

        int total = gas.length;
        for (int i = 0; i < total; ) {

            int totalGas = gas[i];
            int step = cost[i];
            int count = 0;

            while (totalGas >= step) {
                //走一个位置
                totalGas -= step;
                count++;
                //走的步数满一圈
                if (count == total) {
                    return i;
                }
                final int position = (i + count) % total;
                totalGas += gas[position];
                step = cost[position];
            }

            i = i + count + 1;

        }

        return -1;
    }
}

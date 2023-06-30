package com.mikegu.algorithm.leecode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Introduction:
 *
 * @author sesame 2023/6/30
 */
public class LC2611 {

    public static void main(String[] args) {
        int[] reward1 = {1, 2, 1, 2, 1, 2};
        int[] reward2 = {2, 1, 1, 2, 2, 1};
        int k = 0;
        final int i = new LC2611().miceAndCheese(reward1, reward2, k);
        System.out.println(i);
    }

    // 计算得到差值的 数组differ[] =  reward1[] - reward2   时间复杂度为O(n)
    // 对differ建堆取 top k，并存储这K个位置，时间复杂度为 n long(k)
    // 这K位置用reward1，另外一个用reward2
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        //if (k == 0) {
        //    return 0;
        //}

        int[] differ = new int[reward1.length];
        for (int i = 0; i < reward1.length; i++) {
            differ[i] = reward1[i] - reward2[i];
        }

        PriorityQueue<Pair> priorityQueue = new PriorityQueue(Comparator.comparingInt((Pair o) -> o.value));

        // 初始化堆
        for (int i = 0; i < k; i++) {
            priorityQueue.add(new Pair(i, differ[i]));
        }

        for (int i = k; i < differ.length; i++) {
            if (k == 0) {
                break;
            }
            Pair top = priorityQueue.peek();
            if (top.value < differ[i]) {
                priorityQueue.poll();
                priorityQueue.add(new Pair(i, differ[i]));
            }
        }

        Set<Integer> rewardsOneSet = new HashSet<>();
        priorityQueue.forEach(one -> rewardsOneSet.add(one.index));

        int sum = 0;
        for (int i = 0; i < reward1.length; i++) {
            if (rewardsOneSet.contains(i)) {
                sum += reward1[i];
            } else {
                sum += reward2[i];
            }
        }

        return sum;
    }

    class Pair {
        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }

        int index;
        int value;
    }

}

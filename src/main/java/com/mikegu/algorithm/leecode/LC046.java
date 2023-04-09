package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Introduction: 使用回溯的思想， 递归实现
 *
 * @author sesame 2023/4/9
 */
public class LC046 {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        final List<List<Integer>> permute = new LC046().permute(nums);
        System.out.println(permute);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> remaining = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> oneResult = new ArrayList<>();
        processData(remaining, oneResult, result);

        return result;
    }

    private void processData(List<Integer> remaining, List<Integer> oneResult, List<List<Integer>> result) {
        if (remaining.size() == 1) {
            List<Integer> t = new ArrayList<>(oneResult);
            t.add(remaining.get(0));
            result.add(t);
            return;
        }

        for (int i = 0; i < remaining.size(); i++) {
            List<Integer> nextResult = new ArrayList<>(oneResult);
            nextResult.add(remaining.get(i));

            List<Integer> newRemaining = new ArrayList<>(remaining);
            newRemaining.remove(remaining.get(i));

            processData(newRemaining, nextResult, result);
        }

    }
}

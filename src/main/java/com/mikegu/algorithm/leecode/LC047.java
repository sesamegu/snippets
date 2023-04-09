package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Introduction: 在46题的基础上，使用HashSet 解决了过滤问题，不过性能一般；
 * 可以在生成过程中，准对某一位，重复的数字只填一次来提升
 *
 * @author sesame 2023/4/9
 */
public class LC047 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 3};
        final List<List<Integer>> permute = new LC047().permuteUnique(nums);
        System.out.println(permute);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> remaining = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> oneResult = new ArrayList<>();
        processData2(remaining, oneResult, result);

        return result;
    }

    private void processData2(List<Integer> remaining, List<Integer> oneResult, List<List<Integer>> result) {
        if (remaining.size() == 1) {
            List<Integer> t = new ArrayList<>(oneResult);
            t.add(remaining.get(0));
            result.add(t);
            return;
        }

        for (int i = 0; i < remaining.size(); i++) {
            //如果前面已填充过这个数字，跳过
            int current = remaining.get(i);
            boolean isProcessed = false;
            for (int j = 0; j < i; j++) {
                if (remaining.get(j) == current) {
                    isProcessed = true;
                    break;
                }
            }
            if (isProcessed) {
                continue;
            }

            List<Integer> nextResult = new ArrayList<>(oneResult);
            nextResult.add(current);

            List<Integer> newRemaining = new ArrayList<>(remaining);
            newRemaining.remove(remaining.get(i));

            processData2(newRemaining, nextResult, result);
        }

    }
}

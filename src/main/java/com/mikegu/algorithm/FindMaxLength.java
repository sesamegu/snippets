package com.mikegu.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类说明: 时间复杂度 k*k 或 n 的大值
 *
 * @author guhaiquan 2021/3/5
 */
public class FindMaxLength {

    public static void main(String[] argv) {

        int k = 2; //数量
        List<Integer> nood = new ArrayList<>();//木头列表
        nood.add(4);
        nood.add(7);
        nood.add(2);
        nood.add(10);
        nood.add(5);
        int max = new FindMaxLength().flow(nood, k);

        System.out.println("find max: " + max);
    }

    public int flow(List<Integer> nood, int k) {
        //找出最大的top k
        List<Integer> topK = findTopK(nood, k);

        //依次找到max m
        int maxM = topK.get(0);

        for (int i = 1; i < k; i++) {
            if (i == k - 1) {
                maxM = topK.get(i) / k > maxM ? topK.get(i) / k : maxM;
                break;
            }
            int tmpMax = topK.get(i);
            int count = 1;
            for (int j = i + 1; j < k; j++) {
                count = topK.get(j) / tmpMax + count;
                if (count >= k && tmpMax > maxM) {
                    maxM = tmpMax;
                    continue;
                }
            }
        }
        return maxM;
    }

    private List<Integer> findTopK(List<Integer> nood, int k) {
        //todo 可以优化性能n log(n) 到 k*k
        Collections.sort(nood);
        return nood.subList(nood.size() - k, nood.size());
    }


}

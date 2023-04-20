package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduction:
 *
 * @author sesame 2023/4/18
 */
public class LC060 {

    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;
        StringBuffer ans = new StringBuffer();

        List<Integer> remain = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            remain.add(i);
        }

        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;
            Integer remove = remain.remove(order - 1);
            ans.append(remove);
            k %= factorial[n - i];
        }
        return ans.toString();
    }

}

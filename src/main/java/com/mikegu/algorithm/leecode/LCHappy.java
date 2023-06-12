package com.mikegu.algorithm.leecode;

import java.util.HashSet;
import java.util.Set;

/**
 * Introduction:
 *
 * @author sesame 2023/6/6
 */
public class LCHappy {

    public static void main(String[] args) {
        final boolean happy = new LCHappy().isHappy(19);
        System.out.println(happy);
    }

    public boolean isHappy(int n) {

        Set<Integer> containSet = new HashSet<>();

        while (true) {
            if (n == 1) {
                return true;
            }

            if (containSet.contains(n)) {
                return false;
            }
            containSet.add(n);

            int next = 0;
            while (n > 0) {
                int digital = n % 10;
                next += digital * digital;
                n = n / 10;
            }

            n = next;
        }
    }

}

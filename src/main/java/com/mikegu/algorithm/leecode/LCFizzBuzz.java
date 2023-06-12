package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduction:
 *
 * @author sesame 2023/6/6
 */
public class LCFizzBuzz {

    public static void main(String[] args) {
        final List<String> strings = new LCFizzBuzz().fizzBuzz(15);
        System.out.println(strings);
    }

    String FizzBuzz = "FizzBuzz";
    String Fizz = "Fizz";
    String Buzz = "Buzz";
    public List<String> fizzBuzz(int n) {

        List<String> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            boolean fiveTimes = i % 5 == 0;
            boolean threeTimes = i % 3 == 0;
            if (threeTimes && fiveTimes) {
                result.add(FizzBuzz);
            } else if (threeTimes) {
                result.add(Fizz);
            } else if (fiveTimes) {
                result.add(Buzz);
            } else {
                result.add(i + "");
            }
        }

        return result;
    }

}

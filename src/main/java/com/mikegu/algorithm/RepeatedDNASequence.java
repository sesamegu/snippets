package com.mikegu.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Introduction: the solution to the the problem:  https://leetcode-cn.com/problems/repeated-dna-sequences/
 *
 * @author sesame 2021/10/8
 */
public class RepeatedDNASequence {


    public static void main(String[] args) {
        String s = "AAAAAAAAAAA";
        RepeatedDNASequence repeatedDNASequence = new RepeatedDNASequence();
        System.out.println(repeatedDNASequence.findRepeatedDnaSequences(s));

    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> existed = new HashSet<>();
        Set<String> added = new HashSet<>();

        List<String> result = new ArrayList<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String oneString = s.substring(i, i + 10);
            if (existed.contains(oneString)) {
                if (added.contains(oneString)) {
                    continue;
                } else {
                    result.add(oneString);
                    added.add(oneString);
                }
            } else {
                existed.add(oneString);
            }
        }

        return result;
    }


}

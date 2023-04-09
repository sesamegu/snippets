package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction: 这个题大意了，以为通过Hash + 一个跳位就解决了，忽略了存在n个需要跳位的情况。
 * 自己的原始的代码在最下面注释里，这个代码是官方的题解
 * 时间复杂度：O(ls×n)
 *
 * @author sesame 2023/4/9
 */
public class LC030 {

    public static void main(String[] args) {
        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] words = new String[] {"fooo", "barr", "wing", "ding", "wing"};
        List<Integer> substring = new LC030().findSubstring(s, words);
        System.out.println(substring);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int number = words.length, wordLength = words[0].length(), totalLength = s.length();
        // n次循环对应n种不同的 单词截取方法
        for (int i = 0; i < wordLength; i++) {
            if (i + number * wordLength > totalLength) {
                break;
            }
            //differ存储的是差异字符及数量，初始化第一次差异
            Map<String, Integer> differ = new HashMap<>();
            for (int j = 0; j < number; j++) {
                String word = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            if (differ.isEmpty()) {
                res.add(i);
            }

            //每次减少第一个，追加新一个，直到字符串结束
            // todo 这里还有一个优化点，对于出现一个不存在的字符，可以直接跳过前面所有，而不是依然跳一个单元格
            for (int start = i + wordLength; start < totalLength - number * wordLength + 1; start += wordLength) {
                //追加新一个
                String word = s.substring(start + (number - 1) * wordLength, start + number * wordLength);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
                //减少第一个
                word = s.substring(start - wordLength, start);
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }

                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

    //public List<Integer> findSubstring(String s, String[] words) {
    //
    //    int index = 0;
    //    int wordLength = words[0].length();
    //    int wordNumber = words.length;
    //
    //    Map<String, Integer> originalMap = new HashMap<>();
    //    for (int i = 0; i < words.length; i++) {
    //        String one = words[i];
    //        if (originalMap.containsKey(one)) {
    //            originalMap.put(one, originalMap.get(one) + 1);
    //        } else {
    //            originalMap.put(one, 1);
    //        }
    //    }
    //
    //    List<Integer> result = new ArrayList<>();
    //
    //    while (index <= s.length()) {
    //        Map<String, Integer> current = new HashMap<>();
    //        if (index + wordLength * wordNumber > s.length()) {
    //            break;
    //        }
    //
    //        int i;
    //        for (i = 1; i <= wordNumber; i++) {
    //            String substring = s.substring(index + (i - 1) * wordLength, index + wordLength * i);
    //            if (originalMap.containsKey(substring)) {
    //                if (current.containsKey(substring)) {
    //                    Integer currentCount = current.get(substring);
    //                    //虽然包含，但是次数已超越，需要跳位
    //                    if (currentCount >= originalMap.get(substring)) {
    //
    //                        index += 1;
    //                        break;
    //                    } else {
    //                        current.put(substring, currentCount + 1);
    //                    }
    //                } else {
    //                    current.put(substring, 1);
    //                }
    //            } else {
    //                index += 1;
    //                break;
    //            }
    //        }
    //
    //        if (i == wordNumber + 1) {
    //            result.add(index);
    //
    //            index +=1;
    //        }
    //
    //    }
    //
    //    return result;
    //}
}

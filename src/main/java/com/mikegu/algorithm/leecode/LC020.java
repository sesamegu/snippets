package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduction:
 *
 * @author sesame 2023/3/11
 */
public class LC020 {

    public boolean isValid(String s) {
        List<Character> stack = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            final char charAt = s.charAt(i);
            if (charAt == '(' || charAt == '[' || charAt == '{') {
                stack.add(charAt);
            } else if (charAt == ')' || charAt == ']' || charAt == '}') {
                if (stack.size() == 0) {
                    return false;
                } else {
                    final Character remove = stack.remove(stack.size() - 1);
                    if (charAt == ')' && remove == '(') {
                        continue;
                    } else if (charAt == ']' && remove == '[') {
                        continue;
                    } else if (charAt == '}' && remove == '{') {
                        continue;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }

        }
        return stack.size() == 0;
    }

}
package com.mikegu.algorithm.leecode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Introduction:
 *
 * @author sesame 2023/3/10
 */
public class LC141 {
    public boolean hasCycle(ListNode head) {

        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return false;
        }

        Set<ListNode> set = new HashSet<>();

        ListNode current = head;
        while (!Objects.isNull(current)) {
            if (set.contains(current)) {
                return true;
            } else {
                set.add(current);
            }

            current = current.next;
        }

        return false;

    }
}

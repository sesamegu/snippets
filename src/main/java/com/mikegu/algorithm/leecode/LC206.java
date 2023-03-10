package com.mikegu.algorithm.leecode;

import java.util.Objects;

/**
 * Introduction:
 *
 * @author sesame 2023/3/9
 */
public class LC206 {

    public ListNode reverseList(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        //第一个节点特殊处理
        ListNode last = null;
        ListNode current = head;
        ListNode next = head.next;

        head.next = null;

        while (!Objects.isNull(next)){
            //状态转移
            last = current;
            current = next;
            next = next.next;

            //赋值
            current.next = last;
        }


        return current;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

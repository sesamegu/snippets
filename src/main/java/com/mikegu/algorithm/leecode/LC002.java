package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Introduction: 因为数字太大用int/long会越界，所以要用数组来处理. BigDecimal也是类似的处理
 *
 * @author sesame 2023/3/13
 */
public class LC002 {

    public static void main(String[] args) {
        final LC002 lc002 = new LC002();

        int[] l1 = new int[] {9, 9, 9, 9, 9, 9, 9};
        int[] l2 = new int[] {9, 9, 9, 9};

        ListNode listNode = lc002.addTwoNumbers(lc002.buildLink(l1), lc002.buildLink(l2));
        listNode.print();

    }

    /**
     * 转换数组后解决，比较好的解决数据正序和逆向
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> dataOne = new ArrayList<>();
        ListNode node = l1;
        while (node != null) {
            dataOne.add(node.val);
            node = node.next;
        }

        List<Integer> dataTwo = new ArrayList<>();
        node = l2;
        while (node != null) {
            dataTwo.add(node.val);
            node = node.next;
        }

        List<Integer> targetList = new ArrayList<>();
        boolean hasCarryBit = false;
        final int oneSize = dataOne.size();
        final int twoSize = dataTwo.size();
        //相交部分
        for (int i = 0; i < oneSize && i < twoSize; i++) {
            int digital = dataOne.get(i) + dataTwo.get(i);
            if (hasCarryBit) {
                digital += 1;
            }

            if (digital >= 10) {
                hasCarryBit = true;
                digital = digital % 10;
            } else {
                hasCarryBit = false;
            }

            targetList.add(digital);
        }

        //不相交部分
        if (oneSize != twoSize) {
            int startPoint;
            int endPoint;
            List<Integer> data;
            if (oneSize > twoSize) {
                startPoint = twoSize;
                endPoint = oneSize;
                data = dataOne;
            } else {
                startPoint = oneSize;
                endPoint = twoSize;
                data = dataTwo;
            }

            for (int i = startPoint; i < endPoint; i++) {
                int digital = data.get(i);
                if (hasCarryBit) {
                    digital += 1;
                }

                if (digital >= 10) {
                    hasCarryBit = true;
                    digital = digital % 10;
                } else {
                    hasCarryBit = false;
                }

                targetList.add(digital);
            }

        }
        if (hasCarryBit) {
            targetList.add(1);
        }

        //数组转链表
        ListNode currentNode = new ListNode(targetList.get(0));
        ListNode theFirstNode = currentNode;
        for (int i = 1; i < targetList.size(); i++) {
            currentNode.next = new ListNode(targetList.get(i));
            currentNode = currentNode.next;
        }
        currentNode.next = null;

        return theFirstNode;
    }

    public ListNode addTwoNumbersInproved(ListNode l1, ListNode l2) {
        //todo 不借助数组的方式
        return null;
    }

    public ListNode buildLink(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return null;
        }
        ListNode currentNode = new ListNode(array[0]);
        ListNode theFirstNode = currentNode;
        for (int i = 1; i < array.length; i++) {
            //是否最后一个节点
            currentNode.next = new ListNode(array[i]);
            currentNode = currentNode.next;
        }
        currentNode.next = null;

        return theFirstNode;
    }

}



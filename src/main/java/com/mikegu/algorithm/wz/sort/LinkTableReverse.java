package com.mikegu.algorithm.wz.sort;

import lombok.Data;

/**
 * Introduction: 反转一个单链表
 *
 * @author sesame 2022/4/2
 */
public class LinkTableReverse {

    public static void main(String[] args) {

        //初始化一个单向链表
        Node<Integer> last = null;
        int LINK_SIZE = 10;
        for (int i = LINK_SIZE; i >= 2; i--) {
            Node<Integer> currentNode = new Node<>(i, last);
            last = currentNode;
        }
        Node<Integer> first = new Node<>(1, last);
        printLinkTable(first);

        //逆转
        Node<Integer> reversedFirst = reverse(first);
        printLinkTable(reversedFirst);
    }

    /**
     * 打印链表
     *
     * @param first 链表的第一个节点
     */
    public static void printLinkTable(Node<Integer> first) {
        Node<Integer> current = first;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }

    /**
     * 逆转链表
     *
     * @return
     */
    public static Node<Integer> reverse(Node first) {
        //空序列
        if (first == null) {
            return null;
        }

        //第一个节点特殊处理
        Node<Integer> lastNode = null;
        Node<Integer> currentNode = first;
        Node<Integer> next = currentNode.getNext();
        currentNode.setNext(lastNode);

        //循环处理剩余的
        while (next != null) {
            //状态转移
            lastNode = currentNode;
            currentNode = next;
            next = currentNode.getNext();

            //设置next
            currentNode.setNext(lastNode);
        }

        return currentNode;
    }

}

@Data
class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

}



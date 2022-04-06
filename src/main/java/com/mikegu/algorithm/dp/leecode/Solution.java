package com.mikegu.algorithm.dp.leecode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A/B/C/D代表节点 a代表节点A的值，b代表节点B的值，
 * f(A) 代表 包含A节点的最大路径
 * t(A,B) 代表 排除A这个方向外的，包含B节点的最大路径
 *
 * f(A) = a + max{0, t(A,parent), t(A,left), T(A,right)}
 * t(C,Parent) = c + max(0, t(parent,UP),t(parent, C的兄弟节点)}
 *
 * 重复子项：对于二叉树的一个节点，最多和3个几点相连；
 * 可以定长度为3n的数组存储所有t(*,*)的值
 *
 * 对于每个t(*,*)的时间平均时间复杂度为O(1)，因为直接取存储的值即可，所有计算所用的t需要的时间为= n
 *
 * 有了这个数组后，对每个节点计算，时间复杂度为O(1)，整体时间复杂度为n
 *
 * @author sesame 2022/4/2
 */

public class Solution {

    public static void main(String[] args) {
        //Integer[] data = new Integer[] {-10, 9, 20, null, null, 15, 7};
        Integer[] data = new Integer[] {1, 2};
        TreeNode treeNode = buildOneTree(data);
        new Solution().maxPathSum(treeNode);
    }

    public static TreeNode buildOneTree(Integer[] data) {
        TreeNode[] tree = new TreeNode[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] == null) {
                continue;
            }

            TreeNode node = new TreeNode(data[i]);
            tree[i] = node;

            int leftPosition = (i + 1) * 2 - 1;
            if (leftPosition <= data.length - 1) {
                node.left = tree[leftPosition];
            }

            int rightPosition = leftPosition + 1;
            if (rightPosition <= data.length - 1) {
                node.right = tree[rightPosition];
            }
        }

        return tree[0];
    }

    public int maxPathSum(TreeNode root) {
        /**
         * t(A,B) 的值Map，key为节点A，vale{key为A的相邻的三个节点， 内部value为对应的最大路径}
         */
        Map<TreeNode, Map<TreeNode, Integer>> tValues = new LinkedHashMap<>(100000);

        // 检查为空
        if (root == null) {
            return 0;
        }

        /*
         * 每个节点的父节点Map，方便使用，key为节点，value为对应的parent节点
         */
        Map<TreeNode, TreeNode> parentsMap = new HashMap<>();
        // 根节点特殊处理
        parentsMap.put(root, null);
        buildParentMap(root, parentsMap);

        //构建t(*,*)的 map
        buildTmap(root, tValues, parentsMap);

        //计算每个节点的最大值
        Map<TreeNode, Integer> maxValue = new HashMap<>(100000);
        findTheMax(root, parentsMap, tValues, maxValue);

        //遍历树输出最大值
        int max = Integer.MIN_VALUE;
        for (Entry<TreeNode, Integer> one : maxValue.entrySet()) {
            if (one.getValue() > max) {
                max = one.getValue();
            }
        }
        //
        //System.out.println("最大值为 " + max);
        //for (Entry<TreeNode, Map<TreeNode, Integer>> one : tValues.entrySet()) {
        //    System.out.print("node " + one.getKey().val + "\t\t");
        //    Map<TreeNode, Integer> value = one.getValue();
        //    for (Entry<TreeNode, Integer> two : value.entrySet()) {
        //        TreeNode direction = two.getKey();
        //        int value1 = two.getValue();
        //        System.out.print("direction:" + direction.val + " " + value1 + "\t");
        //
        //    }
        //    System.out.println("\t  end!");
        //
        //}

        return max;

    }

    /**
     * 构建每个节点对应的parent的map
     */
    private void buildParentMap(TreeNode node, Map<TreeNode, TreeNode> parentsMap) {
        //处理左节点
        TreeNode left = node.left;
        if (left != null) {
            parentsMap.put(left, node);
            buildParentMap(left, parentsMap);
        }

        //处理右节点
        TreeNode right = node.right;
        if (right != null) {
            parentsMap.put(right, node);
            buildParentMap(right, parentsMap);
        }

    }

    /**
     * 对树进行遍历
     *
     * @param node
     * @param tValues
     */
    private void buildTmap(TreeNode node, Map<TreeNode, Map<TreeNode, Integer>> tValues,
        Map<TreeNode, TreeNode> parentsMap) {

        //遇到空节点结束
        if (node == null) {
            return;
        }

        // 计算t(node, parent), t(node, left), t(node, right)，并保存
        processParent(node, parentsMap, tValues);
        processLeft(node, parentsMap, tValues);
        processRight(node, parentsMap, tValues);

        //递归左节点
        buildTmap(node.left, tValues, parentsMap);
        //递归右节点
        buildTmap(node.right, tValues, parentsMap);

    }

    /**
     * 计算t(node, parent)的值:
     * t(node,parent) = max { value(parent) , t(parent,grandpa), t(parent, node的兄弟节点)}
     */
    private void processParent(TreeNode node, Map<TreeNode, TreeNode> parentsMap,
        Map<TreeNode, Map<TreeNode, Integer>> tValues) {
        TreeNode currentNode = parentsMap.get(node);
        //父节点为空，不需要处理
        if (currentNode == null) {
            return;
        }

        //如果当前节点不存在，初始化
        if (!tValues.containsKey(node)) {
            tValues.put(node, new HashMap<>());
        }

        //已处理过的场景
        Map<TreeNode, Integer> values = tValues.get(node);
        if (values.containsKey(currentNode)) {
            return;
        }
        //只包含当前节点的情况
        int result = currentNode.val;

        //t(parent, grandpa)
        TreeNode grandpa = parentsMap.get(currentNode);
        if (grandpa != null) {
            processParent(currentNode, parentsMap, tValues);
            int parent_grandpa = tValues.get(currentNode).get(grandpa);
            if (parent_grandpa > 0) {
                result = currentNode.val + parent_grandpa;
            }
        }

        //t(parent, node的兄弟节点)
        if (currentNode.left == node) {
            if (currentNode.right != null) {
                processRight(currentNode, parentsMap, tValues);
                int parent_right = tValues.get(currentNode).get(currentNode.right);
                if (parent_right + currentNode.val > result) {
                    result = parent_right + currentNode.val;
                }
            }
        } else if (currentNode.right == node) {
            if (currentNode.left != null) {
                processLeft(currentNode, parentsMap, tValues);
                int parent_left = tValues.get(currentNode).get(currentNode.left);
                if (parent_left + currentNode.val > result) {
                    result = parent_left + currentNode.val;
                }
            }
        } else {
            throw new RuntimeException("some go wrong!");
        }

        // 放入值
        values.put(currentNode, result);
    }

    /**
     * 计算t(node, left)的值:
     * t(node,left) = max { value(left) , t(left, leftLeft), t(left, leftRight)}
     */
    private void processLeft(TreeNode node, Map<TreeNode, TreeNode> parentsMap,
        Map<TreeNode, Map<TreeNode, Integer>> tValues) {
        //空节点直接结束
        TreeNode currentNode = node.left;
        commonProcess(node, parentsMap, tValues, currentNode);
    }

    /**
     * 计算t(node, right)的值:
     * t(node,right) = max { value(right) , t(right, rightLeft), t(left, rightRight)}
     */
    private void processRight(TreeNode node, Map<TreeNode, TreeNode> parentsMap,
        Map<TreeNode, Map<TreeNode, Integer>> tValues) {
        //空节点直接结束
        TreeNode currentNode = node.right;
        commonProcess(node, parentsMap, tValues, currentNode);
    }

    private void commonProcess(TreeNode node, Map<TreeNode, TreeNode> parentsMap,
        Map<TreeNode, Map<TreeNode, Integer>> tValues, TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }

        //如果当前节点不存在，初始化
        if (!tValues.containsKey(node)) {
            tValues.put(node, new HashMap<>());
        }

        //已处理过的场景
        Map<TreeNode, Integer> values = tValues.get(node);
        if (values.containsKey(currentNode)) {
            return;
        }

        //只包含当前节点的情况
        int result = currentNode.val;

        //t(node, nodeLeft)
        if (currentNode.left != null) {
            processLeft(currentNode, parentsMap, tValues);
            int left_left = tValues.get(currentNode).get(currentNode.left);
            if (left_left > 0) {
                result = currentNode.val + left_left;
            }
        }

        //t(node, nodeRight)
        if (currentNode.right != null) {
            processRight(currentNode, parentsMap, tValues);
            int left_right = tValues.get(currentNode).get(currentNode.right);
            if (left_right + currentNode.val > result) {
                result = left_right + currentNode.val;
            }
        }

        // 放入值
        values.put(currentNode, result);
    }

    /**
     * 这里可优化为不使用内存
     */
    private void findTheMax(TreeNode node, Map<TreeNode, TreeNode> parentsMap,
        Map<TreeNode, Map<TreeNode, Integer>> tValues, Map<TreeNode, Integer> maxValue) {
        if (node == null) {
            return;
        }

        int nodeValue = getTheNodeMaxValue(node, parentsMap, tValues);
        maxValue.put(node, nodeValue);

        if (node.left != null) {
            findTheMax(node.left, parentsMap, tValues, maxValue);
        }

        if (node.right != null) {
            findTheMax(node.right, parentsMap, tValues, maxValue);
        }
    }

    private int getTheNodeMaxValue(TreeNode currentNode, Map<TreeNode, TreeNode> parentsMap,
        Map<TreeNode, Map<TreeNode, Integer>> tValues) {
        if (currentNode == null) {
            throw new IllegalArgumentException("");
        }
        int result = currentNode.val;

        //t(A,Parent)
        TreeNode parent = parentsMap.get(currentNode);
        if (parent != null) {
            int parentValue = tValues.get(currentNode).get(parent);
            if (parentValue + currentNode.val > result) {
                result = parentValue + currentNode.val;
            }
        }

        //t(A,left)
        if (currentNode.left != null) {
            int leftValue = tValues.get(currentNode).get(currentNode.left);
            if (leftValue + currentNode.val > result) {
                result = leftValue + currentNode.val;
            }
        }

        //t(A,right)
        if (currentNode.right != null) {
            int rightValue = tValues.get(currentNode).get(currentNode.right);
            if (rightValue + currentNode.val > result) {
                result = rightValue + currentNode.val;
            }
        }

        return result;
    }

}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
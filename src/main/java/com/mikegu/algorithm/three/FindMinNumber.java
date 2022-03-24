package com.mikegu.algorithm.three;

/**
 * Introduction: 解题思路：
 *
 * 1，假设支付支付w元最少需要k硬币，则d[w][k]=min{d[w-v1][k-1] + 1, d[w-v2][k-1] + 1, .... , d[w-vn][k-1] + 1}
 * 2，从总金额一次递减到剩余金额<=0，小于0代表无解，等于0代表有解; /n
 * 3，定义一个长度为w数组，值是boolean，true当前已经再求解的，不需要重复计算；定义一个长度为w数组，值是boolean代表当前要继续求解
 * 重复子问题：剩余相同金额，取到达当前金额的最少次数；
 *
 * @author sesame 2022/3/23
 */
public class FindMinNumber {

    public static void main(String[] args) {

        //总金额
        int totalAmount = 901;
        //硬币币值
        int[] coinType = new int[] {2, 3, 5, 7, 9, 11, 13, 17, 23, 25,30,40,50,100};

        int number = calc(totalAmount, coinType);
        System.out.println("the least number is  " + number);

    }

    /**
     * @param totalAmount 总金额
     * @param coinType 硬币币值
     * @return 硬币数量，-1代表无解；
     */
    public static int calc(int totalAmount, int[] coinType) {
        //访问过的节点，避免重复计算
        boolean[] visited = new boolean[totalAmount + 1];
        //当前处理的节点
        boolean[] current = new boolean[totalAmount + 1];
        //临时变量，存储下一批待处理的节点
        boolean[] next = new boolean[totalAmount + 1];

        //初始化
        visited[totalAmount] = true;
        for (int i = 0; i < coinType.length; i++) {
            int leftAmount = totalAmount - coinType[i];
            if (leftAmount < 0) {
                continue;
            }
            if (leftAmount == 0) {
                return 1;
            }
            current[leftAmount] = true;
            visited[leftAmount] = true;
        }

        //递减查找，直到找到或者遍历了所有可能性
        for (int times = 2; ; times++) {
            //没有能往下递减的情况，代表无解
            if (isAllFalse(current)) {
                return -1;
            }

            for (int k = 0; k < current.length; k++) {
                if (!current[k]) {
                    continue;
                }

                for (int j = 0; j < coinType.length; j++) {
                    int leftAmount = k - coinType[j];
                    if (leftAmount < 0) {
                        continue;
                    }
                    if (leftAmount == 0) {
                        return times;
                    }
                    if (visited[leftAmount]) {
                        continue;
                    }

                    next[leftAmount] = true;
                    visited[leftAmount] = true;
                }

            }

            // 递推到下一轮
            current = next;
            next = new boolean[totalAmount + 1];

        }
    }

    public static boolean isAllFalse(boolean[] current) {
        for (int i = 0; i < current.length; i++) {
            if (current[i]) {
                return false;
            }
        }
        return true;
    }
}

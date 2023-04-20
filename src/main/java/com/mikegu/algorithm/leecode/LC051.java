package com.mikegu.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Introduction: 用回溯法，可以借鉴问题。且鉴于规模，问题不大。
 * 不过check()函数比较消耗性能，可以通过记录已进行的行、列、斜边来达到去重目的
 *
 * @author sesame 2023/4/17
 */
public class LC051 {
    private List<List<String>> result = new ArrayList<>();
    private int size;

    public static void main(String[] args) {
        final List<List<String>> lists = new LC051().solveNQueens(1);
        System.out.println(lists);

    }

    public List<List<String>> solveNQueens(int n) {
        size = n;

        //remaining column
        final List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            columns.add(i);
        }

        queen(0, columns, new ArrayList<String>());
        return result;
    }

    private void queen(int current, List<Integer> columns, ArrayList<String> solution) {
        if (current == size) {
            result.add(solution);
            return;
        }

        for (Integer i : columns) {
            if (legal(current, i, solution)) {
                ArrayList<String> nextResult = new ArrayList<>(solution);
                nextResult.add(buildResult(i));
                final ArrayList<Integer> nextColumns = new ArrayList<>(columns);
                nextColumns.remove(i);
                queen(current + 1, nextColumns, nextResult);
            } else {
                continue;
            }
        }

    }

    private boolean legal(int row, int column, ArrayList<String> solution) {
        //左上斜线
        int nextRow = row - 1;
        int nextColumn = column - 1;
        while (nextRow >= 0 && nextColumn >= 0) {
            if (solution.get(nextRow).charAt(nextColumn) == 'Q') {
                return false;
            }
            nextRow -= 1;
            nextColumn -= 1;
        }
        //右上斜线
        nextRow = row - 1;
        nextColumn = column + 1;
        while (nextRow >= 0 && nextColumn < size) {
            if (solution.get(nextRow).charAt(nextColumn) == 'Q') {
                return false;
            }
            nextRow -= 1;
            nextColumn += 1;
        }

        return true;
    }

    private String buildResult(int position) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (position == i) {
                sb.append("Q");
            } else {
                sb.append(".");
            }

        }
        return sb.toString();

    }
}

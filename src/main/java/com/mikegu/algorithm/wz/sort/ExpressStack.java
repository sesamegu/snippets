package com.mikegu.algorithm.wz.sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.http.util.Asserts;

/**
 * Introduction: 用栈解决计算表达的求解
 *
 * @author sesame 2023/3/10
 */
public class ExpressStack {
    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char MULTIPLY = '*';
    public static final char DIVIDE = '/';

    public static final Set<Character> signSet = new HashSet<>();

    static {
        signSet.add(PLUS);
        signSet.add(MINUS);
        signSet.add(MULTIPLY);
        signSet.add(DIVIDE);
    }

    public static void main(String[] args) {
        //String expression = "1+2*3-10/5+1";
        String expression = "1001*1+2*3-10/5+1+5";
        int calc = new ExpressStack().calc(expression);

        System.out.println("the result is " + calc);
    }

    public int calc(String expression) {
        //todo 这里没做表达式的格式检查，默认是正确的
        Stack<Integer> numbers = new Stack<>(10);
        Stack<Character> signs = new Stack<>(10);

        for (int pos = 0; pos < expression.length(); pos++) {
            char c = expression.charAt(pos);

            //识别符号和数字
            if (isSign(c)) {
                Character peak = signs.peak();

                //操作附 栈为空 或者新的操作附优先级更高
                if (Objects.isNull(peak) || signHigh(c, peak)) {
                    signs.push(c);
                } else {
                    // 递推压栈
                    while (!Objects.isNull(peak) && !signHigh(c, peak)) {
                        calcPeak(numbers, peak);
                        signs.pop();
                        peak = signs.peak();
                    }
                    signs.push(c);
                }

            } else {
                int end = pos;
                for (int i = pos; i < expression.length(); i++) {
                    if (!isSign(expression.charAt(i))) {
                        end = i + 1;
                    } else {
                        break;
                    }
                }
                int digital = Integer.parseInt(expression.substring(pos, end));
                // 跳位
                pos = end - 1;
                numbers.push(digital);
            }

        }
        //最后一次计算
        calcPeak(numbers, signs.pop());

        // 获取结果
        Integer pop = numbers.pop();
        Asserts.check(Objects.isNull(numbers.peak()), "numbers is null");
        Asserts.check(Objects.isNull(signs.peak()), "signs is null");
        return pop;
    }

    private void calcPeak(Stack<Integer> numbers, Character peak) {
        int numberTwo = numbers.pop();
        int numberOne = numbers.pop();
        if (peak == PLUS) {
            numbers.push(numberOne + numberTwo);
        } else if (peak == MINUS) {
            numbers.push(numberOne - numberTwo);
        } else if (peak == MULTIPLY) {
            numbers.push(numberOne * numberTwo);
        } else if (peak == DIVIDE) {
            numbers.push(numberOne / numberTwo);
        } else {
            throw new RuntimeException("illegal sign");
        }
    }

    public boolean isSign(char oneChar) {
        if (signSet.contains(oneChar)) {
            return true;
        }
        return false;
    }

    public boolean signHigh(char first, char second) {
        int firstValue = convert(first);
        int secondValue = convert(second);

        return firstValue > secondValue;
    }

    public int convert(char sign) {

        if (sign == PLUS || sign == MINUS) {
            return 1;
        } else {

            if (sign == MULTIPLY || sign == DIVIDE) {
                return 2;
            } else {
                throw new RuntimeException("illegal sign");
            }
        }
    }

    class Stack<T> {

        List<T> list;
        int index;

        public Stack(int size) {
            list = new ArrayList<T>(size);
            index = -1;
        }

        public void push(T item) {
            list.add(item);
            index++;
        }

        public T pop() {
            if (index < 0) {
                return null;
            }

            T remove = list.remove(index);
            index--;
            return remove;
        }

        public T peak() {
            if (index < 0) {
                return null;
            }
            return list.get(index);
        }

    }

}

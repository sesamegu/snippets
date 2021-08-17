package com.mikegu.poker.base;

import com.mikegu.poker.calculate.CalcHelper;
import com.mikegu.poker.type.ResultEnum;
import com.mikegu.poker.type.TypeResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.util.Asserts;
import org.springframework.util.Assert;

/**
 * Introduction:
 *
 * @author sesame 2021/8/14
 */
public class TypeCompares {

    private static Map<ResultEnum, Comparator> compareMap = new HashMap<>();

    static {
        compareMap.put(ResultEnum.ROYAL_FLUSH, new RoyalFlushCompare());
        compareMap.put(ResultEnum.STRAIGHT_FLUSH, new StraightFlushCompare());
        compareMap.put(ResultEnum.FOUR_OF_A_KIND, new FourOfAKindCompare());
        compareMap.put(ResultEnum.FULL_HOUSE, new FullHouseCompare());
        compareMap.put(ResultEnum.FLUSH, new FlushCompare());
        compareMap.put(ResultEnum.STRAIGHT, new StraightCompare());
        compareMap.put(ResultEnum.THREE_OF_A_KIND, new ThreeOfAKindCompare());
        compareMap.put(ResultEnum.TWO_PAIRS, new TwoPairsCompare());
        compareMap.put(ResultEnum.ONE_PAIR, new OnePairCompare());
        compareMap.put(ResultEnum.HIGH_CARD, new HighCardCompare());
    }

    public static int compare(TypeResult typeResult1, TypeResult typeResult2) {
        Asserts.notNull(typeResult1, "not null");
        Asserts.notNull(typeResult2, "not null");
        if (typeResult1.getResultEnum() != typeResult2.getResultEnum()) {
            return typeResult1.getResultEnum().getOrder() - typeResult2.getResultEnum().getOrder();
        }
        return compareMap.get(typeResult1.getResultEnum()).compare(typeResult1, typeResult2);
    }

    private static int cardNumberCompare(int number1, int number2) {
        if (number1 == number2) {
            return 0;
        }
        if (number1 == CardNumber.A) {
            return 1;
        }
        if (number2 == CardNumber.A) {
            return -1;
        }

        return number1 - number2;

    }

    static class RoyalFlushCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    static class StraightFlushCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            Collections.sort(o1.getFiveCards());
            Collections.sort(o2.getFiveCards());
            return o1.getFiveCards().get(4).getNumber() - o2.getFiveCards().get(4).getNumber();
        }
    }

    static class FourOfAKindCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            List<Integer> o1Result = buildList(o1.getFiveCards());
            List<Integer> o2Result = buildList(o2.getFiveCards());

            // first compare the four
            if (o1Result.get(0) != o2Result.get(0)) {
                return cardNumberCompare(o1Result.get(0), o2Result.get(0));
            }
            //the compare the one
            return cardNumberCompare(o1Result.get(1), o2Result.get(1));
        }

        private List<Integer> buildList(List<Card> fiveCards) {
            Map<Integer, Integer> mapOne = CalcHelper.buildCardNumberMap(fiveCards);
            int fourValue = 0;
            int oneValue = 0;
            for (Entry<Integer, Integer> one : mapOne.entrySet()) {
                if (one.getValue() == 4) {
                    fourValue = one.getKey();
                }
                if (one.getValue() == 1) {
                    oneValue = one.getKey();
                }
            }
            Assert.isTrue(fourValue != 0);
            Assert.isTrue(oneValue != 0);
            List<Integer> result = new ArrayList<>();
            result.add(fourValue);
            result.add(oneValue);

            return result;
        }

    }

    static class FullHouseCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            List<Integer> o1Result = buildList(o1.getFiveCards());
            List<Integer> o2Result = buildList(o2.getFiveCards());

            for (int i = 0; i < o1Result.size(); i++) {
                if (o1Result.get(i) != o2Result.get(i)) {
                    return cardNumberCompare(o1Result.get(i), o2Result.get(i));
                }
            }
            return 0;
        }

        private List<Integer> buildList(List<Card> fiveCards) {
            Map<Integer, Integer> mapOne = CalcHelper.buildCardNumberMap(fiveCards);
            int fourValue = 0;
            int oneValue = 0;
            for (Entry<Integer, Integer> one : mapOne.entrySet()) {
                if (one.getValue() == 3) {
                    fourValue = one.getKey();
                }
                if (one.getValue() == 2) {
                    oneValue = one.getKey();
                }
            }
            Assert.isTrue(fourValue != 0);
            Assert.isTrue(oneValue != 0);
            List<Integer> result = new ArrayList<>();
            result.add(fourValue);
            result.add(oneValue);

            return result;
        }
    }

    static class FlushCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            Collections.sort(o1.getFiveCards());
            Collections.sort(o2.getFiveCards());
            List<Card> o1Cards = o1.getFiveCards();
            List<Card> o2Cards = o2.getFiveCards();
            for (int i = 4; i >=0; i--) {
                if (o1Cards.get(i).compareTo(o2Cards.get(i)) != 0) {
                    return o1Cards.get(i).compareTo(o2Cards.get(i));
                }
            }
            return 0;
        }
    }

    static class StraightCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            Collections.sort(o1.getFiveCards());
            Collections.sort(o2.getFiveCards());
            List<Card> o1Cards = o1.getFiveCards();
            List<Card> o2Cards = o2.getFiveCards();

            // two cards aren't containing A
            if (o1Cards.get(4).getNumber() != CardNumber.A && o2Cards.get(4).getNumber() != CardNumber.A) {
                return o1Cards.get(4).compareTo(o2Cards.get(4));
            }

            // two cards are containing A
            if (o1Cards.get(4).getNumber() == CardNumber.A && o2Cards.get(4).getNumber() == CardNumber.A) {
                return o1Cards.get(3).getNumber() - o2Cards.get(3).getNumber();
            }

            if (o1Cards.get(4).getNumber() == CardNumber.A) {// o1 is containing A
                if (o1Cards.get(3).getNumber() == CardNumber.K) {
                    return 1;
                } else {
                    return -1;
                }
            } else {// o2 is containing A
                if (o2Cards.get(3).getNumber() == CardNumber.K) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }

    }

    static class ThreeOfAKindCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            List<Integer> o1Result = buildList(o1.getFiveCards());
            List<Integer> o2Result = buildList(o2.getFiveCards());
            for (int i = 0; i < o1Result.size(); i++) {
                if (o1Result.get(i) != o2Result.get(i)) {
                    return cardNumberCompare(o1Result.get(i), o2Result.get(i));
                }
            }
            return 0;
        }

        private List<Integer> buildList(List<Card> fiveCards) {
            Map<Integer, Integer> mapOne = CalcHelper.buildCardNumberMap(fiveCards);
            int threeValue = 0;
            List<Integer> oneValue = new ArrayList<>();
            for (Entry<Integer, Integer> one : mapOne.entrySet()) {
                if (one.getValue() == 3) {
                    threeValue = one.getKey();
                }
                if (one.getValue() == 1) {
                    oneValue.add(one.getKey());
                }
            }
            Assert.isTrue(threeValue != 0);
            Assert.isTrue(oneValue.size() == 2);
            List<Integer> result = new ArrayList<>();
            result.add(threeValue);
            Collections.sort(oneValue, (Integer o1, Integer o2) -> -cardNumberCompare(o1, o2));

            result.addAll(oneValue);
            return result;
        }
    }

    static class TwoPairsCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            List<Integer> o1Result = buildList(o1.getFiveCards());
            List<Integer> o2Result = buildList(o2.getFiveCards());
            for (int i = 0; i < o1Result.size(); i++) {
                if (o1Result.get(i) != o2Result.get(i)) {
                    return cardNumberCompare(o1Result.get(i), o2Result.get(i));
                }
            }
            return 0;
        }

        private List<Integer> buildList(List<Card> fiveCards) {
            Map<Integer, Integer> mapOne = CalcHelper.buildCardNumberMap(fiveCards);
            List<Integer> twoValue = new ArrayList<>();
            List<Integer> oneValue = new ArrayList<>();
            for (Entry<Integer, Integer> one : mapOne.entrySet()) {
                if (one.getValue() == 2) {
                    twoValue.add(one.getKey());
                }
                if (one.getValue() == 1) {
                    oneValue.add(one.getKey());
                }
            }
            Assert.isTrue(twoValue.size() == 2);
            Assert.isTrue(oneValue.size() == 1);
            List<Integer> result = new ArrayList<>();
            Collections.sort(twoValue, (Integer o1, Integer o2) -> -cardNumberCompare(o1, o2));
            result.addAll(twoValue);

            Collections.sort(oneValue, (Integer o1, Integer o2) -> -cardNumberCompare(o1, o2));
            result.addAll(oneValue);
            return result;
        }
    }

    static class OnePairCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            List<Integer> o1Result = buildList(o1.getFiveCards());
            List<Integer> o2Result = buildList(o2.getFiveCards());
            for (int i = 0; i < o1Result.size(); i++) {
                if (o1Result.get(i) != o2Result.get(i)) {
                    return cardNumberCompare(o1Result.get(i), o2Result.get(i));
                }
            }
            return 0;
        }

        private List<Integer> buildList(List<Card> fiveCards) {
            Map<Integer, Integer> mapOne = CalcHelper.buildCardNumberMap(fiveCards);
            List<Integer> twoValue = new ArrayList<>();
            List<Integer> oneValue = new ArrayList<>();
            for (Entry<Integer, Integer> one : mapOne.entrySet()) {
                if (one.getValue() == 2) {
                    twoValue.add(one.getKey());
                }
                if (one.getValue() == 1) {
                    oneValue.add(one.getKey());
                }
            }
            Assert.isTrue(twoValue.size() == 1);
            Assert.isTrue(oneValue.size() == 3);
            List<Integer> result = new ArrayList<>();
            Collections.sort(twoValue, (Integer o1, Integer o2) -> -cardNumberCompare(o1, o2));
            result.addAll(twoValue);

            Collections.sort(oneValue, (Integer o1, Integer o2) -> -cardNumberCompare(o1, o2));
            result.addAll(oneValue);
            return result;
        }
    }

    static class HighCardCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            List<Card> o1Cards = o1.getFiveCards();
            List<Card> o2Cards = o2.getFiveCards();
            for (int i = 4; i >=0; i--) {
                if (o1Cards.get(i).compareTo(o2Cards.get(i)) != 0) {
                    return o1Cards.get(i).compareTo(o2Cards.get(i));
                }
            }
            return 0;
        }
    }
}

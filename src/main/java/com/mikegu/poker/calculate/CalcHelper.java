package com.mikegu.poker.calculate;

import com.mikegu.poker.base.Card;
import com.mikegu.poker.base.CardNumber;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.Assert;

/**
 * Introduction:
 *
 * @author sesame 2021/8/13
 */
public abstract class CalcHelper {

    public static boolean isOnePair(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Map<Integer, Integer> map = buildCardNumberMap(fiveCards);

        return (map.entrySet().stream().filter(one -> one.getValue() == 2).count()) == 1
            && (map.entrySet().stream().filter(one -> one.getValue() == 1).count()) == 3;
    }

    public static boolean isTwoPair(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Map<Integer, Integer> map = buildCardNumberMap(fiveCards);
        return (map.entrySet().stream().filter(one -> one.getValue() == 2).count()) == 2
            && (map.entrySet().stream().filter(one -> one.getValue() == 1).count()) == 1;

    }

    public static boolean threeAndTwo(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Map<Integer, Integer> map = buildCardNumberMap(fiveCards);
        return (map.entrySet().stream().filter(one -> one.getValue() == 3).count()) == 1
            && (map.entrySet().stream().filter(one -> one.getValue() == 2).count()) == 1;
    }

    public static boolean threeAndOneOne(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Map<Integer, Integer> map = buildCardNumberMap(fiveCards);
        return (map.entrySet().stream().filter(one -> one.getValue() == 3).count()) == 1
            && (map.entrySet().stream().filter(one -> one.getValue() == 1).count()) == 2;
    }


    public static boolean threeIsSame(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Map<Integer, Integer> map = buildCardNumberMap(fiveCards);

        return (map.entrySet().stream().filter(one -> one.getValue() == 3).count()) == 1;
    }

    public static boolean fourIsSame(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Map<Integer, Integer> map = buildCardNumberMap(fiveCards);

        return (map.entrySet().stream().filter(one -> one.getValue() == 4).count()) == 1;
    }

    public static boolean biggestIsAK(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Collections.sort(fiveCards);
        return fiveCards.get(4).getNumber() == CardNumber.A && fiveCards.get(3).getNumber() == CardNumber.K;
    }

    public static boolean isStraight(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        Collections.sort(fiveCards);

        // the normal case
        int temp = fiveCards.get(0).getNumber();
        int count = 0;
        for (int i = 1; i < 5; i++) {
            if (fiveCards.get(i).getNumber() != temp + 1) {
                break;
            }
            temp = fiveCards.get(i).getNumber();
            count++;
        }
        if (count == 4) {
            return true;
        }

        // the two cases which are including A
        if (fiveCards.get(0).getNumber() == CardNumber.TEN && fiveCards.get(1).getNumber() == CardNumber.J
            && fiveCards.get(2).getNumber() == CardNumber.Q && fiveCards.get(3).getNumber() == CardNumber.K
            && fiveCards.get(4).getNumber() == CardNumber.A) {
            return true;
        }

        if (fiveCards.get(0).getNumber() == CardNumber.TWO && fiveCards.get(1).getNumber() == CardNumber.THREE
            && fiveCards.get(2).getNumber() == CardNumber.FOUR && fiveCards.get(3).getNumber() == CardNumber.FIVE
            && fiveCards.get(4).getNumber() == CardNumber.A) {
            return true;
        }

        return false;
    }

    public static boolean sameColor(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");
        long colorCount = fiveCards.stream().map(one -> one.getColor()).distinct().count();
        return colorCount == 1;
    }

    public static Map<Integer, Integer> buildCardNumberMap(List<Card> fiveCards) {
        Map<Integer, Integer> map = new HashMap<>(5);
        for (Card oneCard : fiveCards) {
            Integer count = map.get(oneCard.getNumber());
            if (count == null) {
                map.put(oneCard.getNumber(), 1);
            } else {
                map.put(oneCard.getNumber(), count + 1);
            }
        }
        return map;
    }

}

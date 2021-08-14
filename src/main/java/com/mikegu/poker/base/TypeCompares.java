package com.mikegu.poker.base;

import com.mikegu.poker.type.ResultEnum;
import com.mikegu.poker.type.TypeResult;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.util.Asserts;

/**
 * Introduction:
 *
 * @author sesame 2021/8/14
 */
public class TypeCompares {

    private static Map<ResultEnum, Comparator> compareMap = new HashMap<>();

    static {

    }


    public static int compare(TypeResult typeResult1, TypeResult typeResult2) {
        Asserts.notNull(typeResult1, "not null");
        Asserts.notNull(typeResult2, "not null");
        if (typeResult1.getResultEnum() != typeResult2.getResultEnum()) {
            return typeResult1.getResultEnum().getOrder() - typeResult2.getResultEnum().getOrder();
        }
        return compareMap.get(typeResult1.getResultEnum()).compare(typeResult1, typeResult2);
    }

    class RoyalFlushCompare implements Comparator<TypeResult> {
        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    class StraightFlushCompare implements Comparator<TypeResult> {
        public int compare(TypeResult o1, TypeResult o2) {
            Collections.sort(o1.getFiveCards());
            return 0;
        }
    }

    class FourOfAKindCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    class FullHouseCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    class FlushCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    class StraightCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    class ThreeOfAKindCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    class TwoPairsCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }


    class OnePairCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

    class HighCardCompare implements Comparator<TypeResult> {

        public int compare(TypeResult o1, TypeResult o2) {
            return 0;
        }
    }

}

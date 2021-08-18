package com.mikegu.poker.analyse;

import static com.mikegu.poker.base.CardStorage.getCardByPosition;

import com.mikegu.poker.base.Card;
import com.mikegu.poker.calculate.Calculator;
import com.mikegu.poker.type.ResultEnum;
import com.mikegu.poker.type.TypeResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Introduction:
 *
 * @author sesame 2021/8/17
 */
public class AnalyseType {

    public static void theResult() {
        //    HIGH_CARD's count is 1302540, percent is 0.50117739
        //    ONE_PAIR's count is 1098240, percent is 0.42256903
        //    TWO_PAIRS's count is 123552, percent is 0.04753902
        //    THREE_OF_A_KIND's count is 54912, percent is 0.02112845
        //    STRAIGHT's count is 10200, percent is 0.00392465
        //    FLUSH's count is 5108, percent is 0.00196540
        //    FULL_HOUSE's count is 3744, percent is 0.00144058
        //    FOUR_OF_A_KIND's count is 624, percent is 0.00024010
        //    STRAIGHT_FLUSH's count is 36, percent is 0.00001385
        //    ROYAL_FLUSH's count is 4, percent is 0.00000154
        //    total is 2598960
    }

    public static void main(String[] args) {
        analyse();
    }

    private static void analyse() {
        HashMap<ResultEnum, Integer> result = new HashMap<>();
        for (int i = 1; i <= 48; i++) {
            for (int j = i + 1; j <= 49; j++) {
                for (int k = j + 1; k <= 50; k++) {
                    for (int m = k + 1; m <= 51; m++) {
                        for (int n = m + 1; n <= 52; n++) {
                            List<Integer> positions = new ArrayList<>(5);
                            positions.add(i);
                            positions.add(j);
                            positions.add(k);
                            positions.add(m);
                            positions.add(n);
                            List<Card> fiveCards = getCardByPosition(positions);
                            TypeResult typeResult = Calculator.calc(fiveCards);
                            Integer count = result.get(typeResult.getResultEnum());
                            if (count == null) {
                                result.put(typeResult.getResultEnum(), 1);
                            } else {
                                result.put(typeResult.getResultEnum(), count + 1);
                            }
                        }
                    }
                }
            }
        }
        int total = 0;
        for (ResultEnum oneResult : ResultEnum.values()) {
            int value = result.get(oneResult);
            BigDecimal percent = new BigDecimal(value / 2598960.0);
            System.out.println(oneResult + "'s count is " + value + ", percent is " + String.format("%.8f", percent));
            total += value;
        }

        System.out.println("total is " + total);
    }

}

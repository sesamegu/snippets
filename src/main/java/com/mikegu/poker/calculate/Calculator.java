package com.mikegu.poker.calculate;

import static com.mikegu.poker.calculate.CalcHelper.biggestIsA;
import static com.mikegu.poker.calculate.CalcHelper.fourIsSame;
import static com.mikegu.poker.calculate.CalcHelper.isOnePair;
import static com.mikegu.poker.calculate.CalcHelper.isStraight;
import static com.mikegu.poker.calculate.CalcHelper.isTwoPair;
import static com.mikegu.poker.calculate.CalcHelper.sameColor;
import static com.mikegu.poker.calculate.CalcHelper.threeAndOneOne;
import static com.mikegu.poker.calculate.CalcHelper.threeAndTwo;

import com.mikegu.poker.base.Card;
import com.mikegu.poker.type.ResultEnum;
import com.mikegu.poker.type.TypeResult;
import java.util.List;
import org.springframework.util.Assert;

/**
 * ÀàËµÃ÷:
 *
 * @author guhaiquan 2021/8/12
 */
public class Calculator {

    public static TypeResult calc(List<Card> fiveCards) {
        Assert.isTrue((fiveCards != null) && (fiveCards.size() == 5), "should be five cards");

        if (sameColor(fiveCards)) {
            if (isStraight(fiveCards)) {
                if (biggestIsA(fiveCards)) {
                    return new TypeResult(ResultEnum.ROYAL_FLUSH, fiveCards);
                } else {
                    return new TypeResult(ResultEnum.STRAIGHT_FLUSH, fiveCards);
                }
            } else {
                return new TypeResult(ResultEnum.FLUSH, fiveCards);
            }
        }

        if (fourIsSame(fiveCards)) {
            return new TypeResult(ResultEnum.FOUR_OF_A_KIND, fiveCards);
        }

        if (threeAndTwo(fiveCards)) {
            return new TypeResult(ResultEnum.FULL_HOUSE, fiveCards);
        }

        if (threeAndOneOne(fiveCards)) {
            return new TypeResult(ResultEnum.THREE_OF_A_KIND, fiveCards);
        }

        if (isStraight(fiveCards)) {
            return new TypeResult(ResultEnum.STRAIGHT, fiveCards);
        }

        if (isTwoPair(fiveCards)) {
            return new TypeResult(ResultEnum.TWO_PAIRS, fiveCards);
        }

        if (isOnePair(fiveCards)) {
            return new TypeResult(ResultEnum.ONE_PAIR, fiveCards);
        }

        return new TypeResult(ResultEnum.HIGH_CARD, fiveCards);

    }


}
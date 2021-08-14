package com.mikegu.poker.calculate;

import com.mikegu.poker.base.Card;
import com.mikegu.poker.base.CardColor;
import com.mikegu.poker.base.CardNumber;
import com.mikegu.poker.base.CardStorage;
import com.mikegu.poker.type.ResultEnum;
import com.mikegu.poker.type.TypeResult;
import java.util.ArrayList;
import java.util.List;

/**
 * ÀàËµÃ÷:
 *
 * @author guhaiquan 2021/8/12
 */
public class CalculateFacade {


    public static void main(String[] args) {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        System.out.println("The cards is:"+ fiveCards +".  The type is "+typeResult.getResultEnum().getDesc());

    }
}

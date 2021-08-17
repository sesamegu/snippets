package com.mikegu.poker.calculate;

import com.mikegu.poker.base.Card;
import com.mikegu.poker.base.CardColor;
import com.mikegu.poker.base.CardNumber;
import com.mikegu.poker.base.TypeCompares;
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
//        System.out.println("The cards is:" + fiveCards + ".  The type is " + typeResult.getResultEnum().getDesc());

        List<Card> fiveCards2 = new ArrayList<>(5);
        fiveCards2.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards2.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards2.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards2.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards2.add(new Card(CardNumber.TEN, CardColor.CLUB));
        TypeResult typeResult2 = Calculator.calc(fiveCards2);

        int compare = TypeCompares.compare(typeResult, typeResult2);
        if (compare == 0) {
            System.out.println(typeResult + " is equal to " + typeResult2);
        } else if (compare > 0) {
            System.out.println(typeResult + " is bigger than " + typeResult2);
        } else {
            System.out.println(typeResult + " is smaller than " + typeResult2);
        }


    }
}

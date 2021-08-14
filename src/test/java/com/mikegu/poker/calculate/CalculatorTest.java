package com.mikegu.poker.calculate;

import static org.junit.Assert.assertEquals;

import com.mikegu.poker.base.Card;
import com.mikegu.poker.base.CardColor;
import com.mikegu.poker.base.CardNumber;
import com.mikegu.poker.type.ResultEnum;
import com.mikegu.poker.type.TypeResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testAllTypes() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ROYAL_FLUSH, typeResult.getResultEnum());

        fiveCards =new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT_FLUSH, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FOUR_OF_A_KIND, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FULL_HOUSE, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FLUSH, typeResult.getResultEnum());


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult.getResultEnum());


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.THREE_OF_A_KIND, typeResult.getResultEnum());


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.TWO_PAIRS, typeResult.getResultEnum());


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.SEVEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ONE_PAIR, typeResult.getResultEnum());


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.A, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.SEVEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.HIGH_CARD, typeResult.getResultEnum());


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.J, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult.getResultEnum());


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.THREE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.FOUR, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.FIVE, CardColor.DIAMOND));
        typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult.getResultEnum());

    }

}
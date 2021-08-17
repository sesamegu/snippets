package com.mikegu.poker.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.mikegu.poker.calculate.Calculator;
import com.mikegu.poker.type.ResultEnum;
import com.mikegu.poker.type.TypeResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TypeComparesTest {

    @Test
    public void compare_order() {

        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ROYAL_FLUSH, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT_FLUSH, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FOUR_OF_A_KIND, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult2, typeResult3) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult4 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FULL_HOUSE, typeResult4.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult3, typeResult4) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult5 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FLUSH, typeResult5.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult4, typeResult5) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        TypeResult typeResult6 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult6.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult5, typeResult6) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult7 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.THREE_OF_A_KIND, typeResult7.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult6, typeResult7) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult8 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.TWO_PAIRS, typeResult8.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult7, typeResult8) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.SEVEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult9 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ONE_PAIR, typeResult9.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult8, typeResult9) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.A, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.SEVEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult10 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.HIGH_CARD, typeResult10.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult9, typeResult10) > 0);
    }

    @Test
    public void test_royal_straight() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ROYAL_FLUSH, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ROYAL_FLUSH, typeResult2.getResultEnum());

        assertTrue(TypeCompares.compare(typeResult, typeResult2) == 0);
    }


    @Test
    public void test_straight_flush() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT_FLUSH, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT_FLUSH, typeResult2.getResultEnum());

        assertTrue(TypeCompares.compare(typeResult, typeResult2) > 0);
    }




    @Test
    public void test_four_of_a_kind() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FOUR_OF_A_KIND, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FOUR_OF_A_KIND, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) < 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FOUR_OF_A_KIND, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult3) < 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        TypeResult typeResult4 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FOUR_OF_A_KIND, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult3, typeResult4) == 0);

    }

    @Test
    public void test_full_house() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FULL_HOUSE, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.A, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FULL_HOUSE, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) < 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.A, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FULL_HOUSE, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult2, typeResult3) < 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        TypeResult typeResult4 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FULL_HOUSE, typeResult4.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult4) == 0);
    }


    @Test
    public void test_flush() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FLUSH, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.SEVEN, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.FLUSH, typeResult2.getResultEnum());

        assertTrue(TypeCompares.compare(typeResult, typeResult2) < 0);
    }


    @Test
    public void test_straight() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.CLUB));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.CLUB));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.CLUB));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult3) < 0);


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.THREE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.FOUR, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.FIVE, CardColor.CLUB));
        TypeResult typeResult4 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.STRAIGHT, typeResult4.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult4) > 0);
        assertTrue(TypeCompares.compare(typeResult3, typeResult4) > 0);
    }


    @Test
    public void test_three_of_a_kind() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.THREE_OF_A_KIND, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.A, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.THREE_OF_A_KIND, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) < 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.Q, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.THREE_OF_A_KIND, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult3) < 0);


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult4 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.THREE_OF_A_KIND, typeResult4.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult4) > 0);
        assertTrue(TypeCompares.compare(typeResult3, typeResult4) > 0);

    }



    @Test
    public void test_two_pairs() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.TWO_PAIRS, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.TWO_PAIRS, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) < 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.DIAMOND));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.TWO_PAIRS, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult3) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.DIAMOND));
        TypeResult typeResult4 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.TWO_PAIRS, typeResult4.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult4) > 0);


        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.K, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.K, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.DIAMOND));
        TypeResult typeResult5 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.TWO_PAIRS, typeResult5.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult5) < 0);
    }


    @Test
    public void test_one_pairs() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.A, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.K, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ONE_PAIR, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.A, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.K, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.SEVEN, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ONE_PAIR, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.A, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.K, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ONE_PAIR, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult3) < 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.A, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.Q, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.K, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult4 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.ONE_PAIR, typeResult4.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult4) <0);
    }


    @Test
    public void test_high_card() {
        List<Card> fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.Q, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.J, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        TypeResult typeResult = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.HIGH_CARD, typeResult.getResultEnum());

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.K, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.Q, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.J, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.SEVEN, CardColor.DIAMOND));
        TypeResult typeResult2 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.HIGH_CARD, typeResult2.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult2) > 0);

        fiveCards = new ArrayList<>(5);
        fiveCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
        fiveCards.add(new Card(CardNumber.Q, CardColor.CLUB));
        fiveCards.add(new Card(CardNumber.J, CardColor.SPADE));
        fiveCards.add(new Card(CardNumber.TWO, CardColor.HEART));
        fiveCards.add(new Card(CardNumber.THREE, CardColor.DIAMOND));
        TypeResult typeResult3 = Calculator.calc(fiveCards);
        assertEquals(ResultEnum.HIGH_CARD, typeResult3.getResultEnum());
        assertTrue(TypeCompares.compare(typeResult, typeResult3) > 0);

    }





}
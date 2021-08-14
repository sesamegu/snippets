package com.mikegu.poker.base;

import java.util.ArrayList;
import java.util.List;

/**
 * care storage:
 *
 * @author guhaiquan 2021/8/12
 */
public class CardStorage {

    private static List<Card> allCards = new ArrayList<>(52);

    static {
        //heart
        allCards.add(new Card(CardNumber.A, CardColor.HEART));
        allCards.add(new Card(CardNumber.TWO, CardColor.HEART));
        allCards.add(new Card(CardNumber.THREE, CardColor.HEART));
        allCards.add(new Card(CardNumber.FOUR, CardColor.HEART));
        allCards.add(new Card(CardNumber.FIVE, CardColor.HEART));
        allCards.add(new Card(CardNumber.SIX, CardColor.HEART));
        allCards.add(new Card(CardNumber.SEVEN, CardColor.HEART));
        allCards.add(new Card(CardNumber.EIGHT, CardColor.HEART));
        allCards.add(new Card(CardNumber.NINE, CardColor.HEART));
        allCards.add(new Card(CardNumber.TEN, CardColor.HEART));
        allCards.add(new Card(CardNumber.J, CardColor.HEART));
        allCards.add(new Card(CardNumber.Q, CardColor.HEART));
        allCards.add(new Card(CardNumber.K, CardColor.HEART));

        //SPADE
        allCards.add(new Card(CardNumber.A, CardColor.SPADE));
        allCards.add(new Card(CardNumber.TWO, CardColor.SPADE));
        allCards.add(new Card(CardNumber.THREE, CardColor.SPADE));
        allCards.add(new Card(CardNumber.FOUR, CardColor.SPADE));
        allCards.add(new Card(CardNumber.FIVE, CardColor.SPADE));
        allCards.add(new Card(CardNumber.SIX, CardColor.SPADE));
        allCards.add(new Card(CardNumber.SEVEN, CardColor.SPADE));
        allCards.add(new Card(CardNumber.EIGHT, CardColor.SPADE));
        allCards.add(new Card(CardNumber.NINE, CardColor.SPADE));
        allCards.add(new Card(CardNumber.TEN, CardColor.SPADE));
        allCards.add(new Card(CardNumber.J, CardColor.SPADE));
        allCards.add(new Card(CardNumber.Q, CardColor.SPADE));
        allCards.add(new Card(CardNumber.K, CardColor.SPADE));

        //CLUB
        allCards.add(new Card(CardNumber.A, CardColor.CLUB));
        allCards.add(new Card(CardNumber.TWO, CardColor.CLUB));
        allCards.add(new Card(CardNumber.THREE, CardColor.CLUB));
        allCards.add(new Card(CardNumber.FOUR, CardColor.CLUB));
        allCards.add(new Card(CardNumber.FIVE, CardColor.CLUB));
        allCards.add(new Card(CardNumber.SIX, CardColor.CLUB));
        allCards.add(new Card(CardNumber.SEVEN, CardColor.CLUB));
        allCards.add(new Card(CardNumber.EIGHT, CardColor.CLUB));
        allCards.add(new Card(CardNumber.NINE, CardColor.CLUB));
        allCards.add(new Card(CardNumber.TEN, CardColor.CLUB));
        allCards.add(new Card(CardNumber.J, CardColor.CLUB));
        allCards.add(new Card(CardNumber.Q, CardColor.CLUB));
        allCards.add(new Card(CardNumber.K, CardColor.CLUB));

        //DIAMOND
        allCards.add(new Card(CardNumber.A, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.TWO, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.THREE, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.FOUR, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.FIVE, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.SIX, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.SEVEN, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.EIGHT, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.NINE, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.TEN, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.J, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.Q, CardColor.DIAMOND));
        allCards.add(new Card(CardNumber.K, CardColor.DIAMOND));
    }

    public static List<Card> getCopyCards() {
        return new ArrayList<>(allCards);
    }
}

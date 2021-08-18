package com.mikegu.poker.base;

import lombok.Getter;

/**
 * Card type
 *
 * @author guhaiquan 2021/8/12
 */

@Getter
public class Card implements Comparable<Card> {

    private int number;
    private int color;

    public Card(int number, int color) {
        this.number = number;
        this.color = color;
    }

    public static String number2Sign(int number) {
        switch (number) {
            case CardNumber.A:
                return CardNumber.SIGN_A;
            case CardNumber.TWO:
                return CardNumber.SIGN_2;
            case CardNumber.THREE:
                return CardNumber.SIGN_3;
            case CardNumber.FOUR:
                return CardNumber.SIGN_4;
            case CardNumber.FIVE:
                return CardNumber.SIGN_5;
            case CardNumber.SIX:
                return CardNumber.SIGN_6;
            case CardNumber.SEVEN:
                return CardNumber.SIGN_7;
            case CardNumber.EIGHT:
                return CardNumber.SIGN_8;
            case CardNumber.NINE:
                return CardNumber.SIGN_9;
            case CardNumber.TEN:
                return CardNumber.SIGN_10;
            case CardNumber.J:
                return CardNumber.SIGN_J;
            case CardNumber.Q:
                return CardNumber.SIGN_Q;
            case CardNumber.K:
                return CardNumber.SIGN_K;
            default:
                throw new RuntimeException("Illegal number");
        }
    }


    public static String color2Sign(int color) {
        switch (color) {
            case CardColor.HEART:
                return CardColor.SIGN_HEART;
            case CardColor.SPADE:
                return CardColor.SIGN_SPADE;
            case CardColor.CLUB:
                return CardColor.SIGN_CLUB;
            case CardColor.DIAMOND:
                return CardColor.SIGN_DIAMOND;
            default:
                throw new RuntimeException("Illegal color");
        }
    }


    @Override
    public String toString() {
        return number2Sign(this.number) + color2Sign(this.color);
    }


    @Override
    public int compareTo(Card o2) {
        if (this.getNumber() == o2.getNumber()) {
            return 0;
        }

        if (this.getNumber() == CardNumber.A) {
            return 1;
        }

        if (o2.getNumber() == CardNumber.A) {
            return -1;
        }
        return this.getNumber() - o2.getNumber();

    }

    @Override
    public int hashCode() {
        return number * 3777 + color;
    }

    @Override
    public boolean equals(Object obj) {
        return this.color == ((Card) obj).color && this.number == ((Card) obj).number;
    }
}

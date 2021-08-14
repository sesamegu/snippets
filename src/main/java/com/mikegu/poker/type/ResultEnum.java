package com.mikegu.poker.type;

import lombok.Getter;

/**
 * 类说明: 牌型枚举
 *
 * @author guhaiquan 2021/8/12
 */
@Getter
public enum ResultEnum {
    HIGH_CARD(1, "high card"),
    ONE_PAIR(2, "one pair"),
    TWO_PAIRS(3, "two pairs"),
    THREE_OF_A_KIND(4, "three of a kind"),
    STRAIGHT(5, "straight"),
    FLUSH(6, "flush"),
    FULL_HOUSE(7, "full house"),
    FOUR_OF_A_KIND(8, "four of a kind"),
    STRAIGHT_FLUSH(9, "straight flush"),
    ROYAL_FLUSH(10, "royal flush"),
    ;
    private int order;
    private String desc;

    ResultEnum(int order, String desc) {
        this.order = order;
        this.desc = desc;
    }
}

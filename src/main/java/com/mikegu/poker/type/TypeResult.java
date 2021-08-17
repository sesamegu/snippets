package com.mikegu.poker.type;

import com.mikegu.poker.base.Card;
import java.util.List;
import lombok.Getter;

/**
 * 类说明: 5张牌的结果
 *
 * @author guhaiquan 2021/8/12
 */
@Getter
public class TypeResult {

    private ResultEnum resultEnum;
    private List<Card> fiveCards;

    public TypeResult(ResultEnum resultEnum, List<Card> fiveCards) {
        this.resultEnum = resultEnum;
        this.fiveCards = fiveCards;
    }

    @Override
    public String toString() {
        return "Cards are " + fiveCards + " , the type is " + resultEnum.getDesc();
    }
}

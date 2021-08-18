package com.mikegu.poker.analyse;

import com.mikegu.poker.base.Card;
import com.mikegu.poker.base.CardColor;
import com.mikegu.poker.base.CardNumber;
import com.mikegu.poker.base.CardStorage;
import com.mikegu.poker.base.TypeCompares;
import com.mikegu.poker.calculate.Calculator;
import com.mikegu.poker.type.TypeResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.util.Assert;

/**
 * Introduction:
 *
 * @author sesame 2021/8/17
 */
public class AnalyseTwoCards {

    private static ExecutorService executorService = Executors
        .newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    public static void theResult() {
//  AºìÌÒAÃ·»¨ ¶Ô KºÚÌÒK·½¿é      Win: 1388072. Lose:317694. draw:6538.	Win: 0.8106. Lose:0.1855. draw:0.0038
    }

    public static void flow() throws InterruptedException {
        //the result
        AtomicInteger draw = new AtomicInteger(0);
        AtomicInteger win = new AtomicInteger(0);
        AtomicInteger lose = new AtomicInteger(0);

        // step 1/3:  get the common 5 cards
        List<Card> firstPair = new ArrayList<>();
        firstPair.add(new Card(CardNumber.A, CardColor.HEART));
        firstPair.add(new Card(CardNumber.A, CardColor.CLUB));

        List<Card> secondPair = new ArrayList<>();
        secondPair.add(new Card(CardNumber.K, CardColor.SPADE));
        secondPair.add(new Card(CardNumber.K, CardColor.DIAMOND));

        List<Card> copyCards = CardStorage.getCopyCards();
        copyCards.removeAll(firstPair);
        copyCards.removeAll(secondPair);

//        System.out.println(copyCards);
        AtomicInteger finishedCount = new AtomicInteger(0);
        int totalCount = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 44; i++) {
            for (int j = i + 1; j <= 45; j++) {
                for (int k = j + 1; k <= 46; k++) {
                    for (int m = k + 1; m <= 47; m++) {
                        for (int n = m + 1; n <= 48; n++) {
                            totalCount++;
                            List<Integer> positions = new ArrayList<>(5);
                            positions.add(i);
                            positions.add(j);
                            positions.add(k);
                            positions.add(m);
                            positions.add(n);

                            executorService.execute(
                                () -> {
                                    List<Card> fiveCards = getCardByPosition(positions, copyCards);
                                    List<Card> firstSevenCards = new ArrayList<>(fiveCards);
                                    firstSevenCards.addAll(firstPair);
                                    TypeResult firstResult = calcMax(firstSevenCards);

                                    List<Card> secondSevenCards = new ArrayList<>(fiveCards);
                                    secondSevenCards.addAll(secondPair);
                                    TypeResult secondResult = calcMax(secondSevenCards);

                                    //step 3/3: compare and calc
                                    int compare = TypeCompares.compare(firstResult, secondResult);
                                    if (compare == 0) {
                                        draw.incrementAndGet();
                                    } else if (compare > 0) {
                                        win.incrementAndGet();
                                    } else {
                                        lose.incrementAndGet();
                                    }

                                    int current = finishedCount.incrementAndGet();
                                    if (current % 10000 == 0) {
                                        System.out.print(
                                            "count=" + current + ". Spend "
                                                + (System.currentTimeMillis() - start) / 1000
                                                + " Seconds");
                                        System.out.println(" Win:" + win + ". Lose:" + lose + ". draw:" + draw);
                                    }
                                }
                            );

                        }
                    }
                }
            }
        }
        while (totalCount != finishedCount.get()) {
            TimeUnit.SECONDS.sleep(1);
        }
        executorService.shutdownNow();

        double totalNumber = win.get() + lose.get() + draw.get();
        System.out.print("Win: " + win + ". Lose:" + lose + ". draw:" + draw);
        System.out
            .println(".\tWin: " + String.format("%.4f", new BigDecimal(win.get() / totalNumber)) + ". Lose:" + String
                .format("%.4f", new BigDecimal(lose.get() / totalNumber)) + ". draw:" + String
                .format("%.4f", new BigDecimal(draw.get() / totalNumber)));

    }

    // step 2/3: analyse the biggest for two pairs
    private static TypeResult calcMax(List<Card> sevenCards) {
        TypeResult maxResult = null;
        for (int i = 1; i <= 3; i++) {
            for (int j = i + 1; j <= 4; j++) {
                for (int k = j + 1; k <= 5; k++) {
                    for (int m = k + 1; m <= 6; m++) {
                        for (int n = m + 1; n <= 7; n++) {
                            List<Integer> positions = new ArrayList<>(5);
                            positions.add(i);
                            positions.add(j);
                            positions.add(k);
                            positions.add(m);
                            positions.add(n);
                            List<Card> fiveCards = getCardByPosition(positions, sevenCards);
                            TypeResult result = Calculator.calc(fiveCards);
                            if (maxResult == null) {
                                maxResult = result;
                            } else {
                                if (TypeCompares.compare(maxResult, result) < 0) {
                                    maxResult = result;
                                }
                            }
                        }
                    }
                }
            }
        }
        return maxResult;
    }

    public static List<Card> getCardByPosition(List<Integer> position, List<Card> copyCards) {
        Assert.isTrue(position != null && position.size() == 5, "should be five");
        List<Card> fiveCard = new ArrayList<>();
        for (int one : position) {
            fiveCard.add(copyCards.get(one - 1));
        }
        return fiveCard;
    }

    public static void main(String[] args) throws InterruptedException {
        flow();
    }


}

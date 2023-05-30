package com.mini.card.controller;

import com.mini.card.domain.Card;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CardGameController {

    private int n = 2;
    private List<Card> cards;
    private int matchingPairs;
    private int score;
    private Card flippedCard;

    @GetMapping("/")
    public String game(Model model) {
        initializeGame();

        model.addAttribute("cards", cards);
        model.addAttribute("matchingPairs", matchingPairs);
        model.addAttribute("score", score);
        model.addAttribute("n", n);

        return "game";
    }

    @GetMapping("/flipCard")
    public String flipCard(@RequestParam("cardId") int cardId, Model model) {
        // 카드 뒤집기
        Card selectedCard = getCardById(cardId);
        if (selectedCard != null && !selectedCard.isFlipped() && !selectedCard.isMatched()) {
            selectedCard.setFlipped(true);

            // 일치 여부 확인
            if (flippedCard != null) {
                if (flippedCard.getId() == selectedCard.getId()) {
                    flippedCard.setMatched(true);
                    selectedCard.setMatched(true);
                    matchingPairs++;
                } else {
                    flippedCard.setFlipped(false);
                    selectedCard.setFlipped(false);
                }
                flippedCard = null;
            } else {
                flippedCard = selectedCard;
            }
        }

        model.addAttribute("cards", cards);
        model.addAttribute("matchingPairs", matchingPairs);
        model.addAttribute("score", score);
        model.addAttribute("n", n);

        return "game";
    }

    @GetMapping("/increaseGridSize")
    public String increaseGridSize() {
        n += 2;
        return "redirect:/";
    }

    @GetMapping("/decreaseGridSize")
    public String decreaseGridSize() {
        if (n > 2) {
            n -= 2;
        }
        return "redirect:/";
    }

    private void initializeGame() {
        cards = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                Card cc = new Card(((i - 1) * n + j), "Card " + ((i - 1) * n + j));
                cards.add(cc);
                System.out.println("j = " + cc);
            }
        }

        matchingPairs = 0;
        score = 0;
    }

    private Card getCardById(int cardId) {
        for (Card card : cards) {
            if (card.getId() == cardId) {
                return card;
            }
        }
        return null;
    }
}


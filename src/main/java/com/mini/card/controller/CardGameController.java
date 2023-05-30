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

    private int n = 4; // 초기 그리드 열의 개수
    private List<Card> cards;
    private int matchingPairs;
    private int score;

    @GetMapping("/")
    public String game(Model model) {
        // 게임 초기화
        initializeGame();

        // 모델에 데이터 설정
        model.addAttribute("cards", cards);
        model.addAttribute("matchingPairs", matchingPairs);
        model.addAttribute("score", score);
        model.addAttribute("n", n); // n 값을 전달

        // 템플릿 이름 반환
        return "game";
    }

    @GetMapping("/flipCard")
    public String flipCard(@RequestParam("cardId") int cardId, Model model) {
        // 선택한 카드 뒤집기
        flipCardById(cardId);

        // 모델에 데이터 설정
        model.addAttribute("cards", cards);
        model.addAttribute("matchingPairs", matchingPairs);
        model.addAttribute("score", score);
        model.addAttribute("n", n); // n 값을 전달

        // 템플릿 이름 반환
        return "game";
    }

    @GetMapping("/increaseGridSize")
    public String increaseGridSize() {
        n++;
        return "redirect:/";
    }

    private void initializeGame() {
        // 게임 초기화 로직 작성

        // 예시를 위해 더미 데이터로 카드 생성
        cards = new ArrayList<>();
        for (int i = 1; i <= n*2; i++) {
            cards.add(new Card(i, "Card " + i));
            cards.add(new Card(i, "Card " + i));
        }

        matchingPairs = 0;
        score = 0;
    }

    private void flipCardById(int cardId) {
        // 선택한 카드 뒤집기 로직 작성

        // 예시를 위해 더미 데이터를 업데이트
        for (Card card : cards) {
            if (card.getId() == cardId) {
                card.setFlipped(!card.isFlipped());
                break;
            }
        }
    }
}


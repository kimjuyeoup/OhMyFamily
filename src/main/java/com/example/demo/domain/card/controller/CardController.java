package com.example.demo.domain.card.controller;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.card.entity.Card;
import com.example.demo.domain.card.service.CardCommandService;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.global.exception.BaseResponse;

@RestController
@RequestMapping("/api")
public class CardController {

  private final CardCommandService cardService;
  private final QuizRepository quizRepository;

  public CardController(CardCommandService cardService, QuizRepository quizRepository) {
    this.cardService = cardService;
    this.quizRepository = quizRepository;
  }

  @Transactional
  @GetMapping("/search/detail")
  public BaseResponse<Card> searchDetail(@RequestParam Integer quizid) {
    Long score = quizRepository.findScoreByQuizid(quizid);
    String name = quizRepository.findNameByQuizid(quizid);
    List<Card> cards = cardService.findCardsByScore(score);
    quizRepository.updateCheck(quizid);

    Card card = cards.get(0);
    card.setName(name);

    return BaseResponse.onSuccess(card);
  }
}

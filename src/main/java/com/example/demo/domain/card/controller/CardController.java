package com.example.demo.domain.card.controller;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.card.entity.Card;
import com.example.demo.domain.card.service.CardCommandService;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.global.encrypt.EncryptService;
import com.example.demo.global.exception.BaseResponse;

@RestController
@RequestMapping("/api")
public class CardController {

  private final CardCommandService cardService;
  private final QuizRepository quizRepository;
  private final EncryptService encryptService;

  public CardController(
      CardCommandService cardService,
      QuizRepository quizRepository,
      EncryptService encryptService) {
    this.cardService = cardService;
    this.quizRepository = quizRepository;
    this.encryptService = encryptService;
  }

  @Transactional
  @GetMapping("/search/detail")
  public BaseResponse<Card> searchDetail(
      @RequestHeader("Authorization") String accessToken, @RequestParam String quizid)
      throws Exception {
    Integer id = encryptService.decrypt(quizid).intValue();
    Long score = quizRepository.findScoreByQuizid(id);
    String name = quizRepository.findNameByQuizid(id);
    List<Card> cards = cardService.findCardsByScore(score);
    quizRepository.updateCheck(id);

    Card card = cards.get(0);
    card.setName(name);

    return BaseResponse.onSuccess(card);
  }
}

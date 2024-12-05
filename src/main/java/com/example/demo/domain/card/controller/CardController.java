package com.example.demo.domain.card.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.card.dto.CardDto;
import com.example.demo.domain.card.entity.Card;
import com.example.demo.domain.card.service.CardCommandService;

@RestController
@RequestMapping("/api")
public class CardController {

  private final CardCommandService cardService;

  public CardController(CardCommandService cardService) {
    this.cardService = cardService;
  }

  @GetMapping("/search/detail")
  public List<CardDto> searchDetail(@RequestParam String name, @RequestParam Long score) {
    List<Card> cards = cardService.findCardsByScore(score);

    return cards.stream()
        .map(card -> new CardDto(card, name)) // name 값 추가
        .collect(Collectors.toList());
  }
}

package com.example.demo.domain.card.service;

import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.card.converter.CardConverter;
import com.example.demo.domain.card.dto.request.CardRequest;
import com.example.demo.domain.card.entity.Card;
import com.example.demo.domain.card.repository.CardRepository;

@Service
// @RequiredArgsConstructor
@Transactional
public class CardCommandService {

  private final CardRepository cardRepository;

  public CardCommandService(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  @PostConstruct
  public void init() {
    Card card = new Card();
    card.setContent("a");
    card.setId(1L);
    card.setImageUrl("aa");
    card.setSubtitle("aaa");
    card.setScore(10);
    card.setTitle("aaaa");
    Card card2 = new Card();
    card2.setContent("b");
    card2.setId(2L);
    card2.setImageUrl("bb");
    card2.setSubtitle("bbb");
    card2.setScore(20);
    card2.setTitle("bbbb");
    Card card3 = new Card();
    card3.setContent("c");
    card3.setId(3L);
    card3.setImageUrl("cc");
    card3.setSubtitle("ccc");
    card3.setScore(30);
    card3.setTitle("cccc");
    Card card4 = new Card();
    card4.setContent("b");
    card4.setId(1L);
    card4.setImageUrl("bb");
    card4.setSubtitle("bbb");
    card4.setScore(20);
    card4.setTitle("bbbb");
    Card card5 = new Card();
    card5.setContent("b");
    card5.setId(1L);
    card5.setImageUrl("bb");
    card5.setSubtitle("bbb");
    card5.setScore(20);
    card5.setTitle("bbbb");
    Card card6 = new Card();
    card6.setContent("b");
    card6.setId(1L);
    card6.setImageUrl("bb");
    card6.setSubtitle("bbb");
    card6.setScore(20);
    card6.setTitle("bbbb");

    List<Card> entities = Arrays.asList(card, card2, card3, card4, card5, card6);

    cardRepository.saveAll(entities);
  }

  /*public void createCard(CardRequest request) {
    cardRepository.save(CardConverter.toCard(request));
  }*/

  public void updateCard(CardRequest request) {
    cardRepository.save(CardConverter.toCard(request));
  }

  public void deleteCard(Long cardId) {
    cardRepository.deleteById(cardId);
  }
}

package com.example.demo.domain.card.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.card.converter.CardConverter;
import com.example.demo.domain.card.dto.request.CardRequest;
import com.example.demo.domain.card.repository.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CardCommandService {

  private final CardRepository cardRepository;

  public void createCard(CardRequest request) {
    cardRepository.save(CardConverter.toCard(request));
  }

  public void updateCard(CardRequest request) {
    cardRepository.save(CardConverter.toCard(request));
  }

  public void deleteCard(Long cardId) {
    cardRepository.deleteById(cardId);
  }
}

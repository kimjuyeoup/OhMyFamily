package com.example.demo.domain.card.converter;

import com.example.demo.domain.card.dto.request.CardRequest;
import com.example.demo.domain.card.entity.Card;

public class CardConverter {

  public static Card toCard(CardRequest request) {
    return Card.builder()
        .score(request.getScore())
        .imageUrl(request.getImageUrl())
        .title(request.getTitle())
        .subtitle(request.getSubtitle())
        .content(request.getContent())
        .build();
  }
}

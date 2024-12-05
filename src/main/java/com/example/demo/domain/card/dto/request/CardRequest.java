package com.example.demo.domain.card.dto.request;

import lombok.Getter;

@Getter
public class CardRequest {

  Long score;
  String imageUrl;
  String title;
  String subtitle;
  String content;
}

package com.example.demo.domain.card.dto.request;

import lombok.Getter;

@Getter
public class CardRequest {

  Long score;
  String icon;
  String title;
  String subtitle;
  String content;
}

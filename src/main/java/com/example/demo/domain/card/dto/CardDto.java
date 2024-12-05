package com.example.demo.domain.card.dto;

import com.example.demo.domain.card.entity.Card;

public class CardDto {
  private Long id;
  private Long score;
  private String imageurl;
  private String title;
  private String subtitle;
  private String content;
  private String name;

  public CardDto(Card card, String name) {
    this.id = card.getId();
    this.score = card.getScore();
    this.imageurl = card.getImageUrl();
    this.title = card.getTitle();
    this.subtitle = card.getSubtitle();
    this.content = card.getContent();
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

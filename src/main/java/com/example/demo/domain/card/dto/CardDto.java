package com.example.demo.domain.card.dto;

import com.example.demo.domain.card.entity.Card;

public class CardDto {
  private Long id;
  private Long score;
  private String icon;
  private String title;
  private String subtitle;
  private String content;
  private String name;

  public CardDto(Card card, String name) {
    this.id = card.getId();
    this.score = card.getScore();
    this.icon = card.getIcon();
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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
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

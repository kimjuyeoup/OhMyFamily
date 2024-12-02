package com.example.demo.domain.Question.dto;

public class QuestionDto {
  private Long id;
  private String title;
  private String content;
  private String type;
  private String icon;

  public QuestionDto(Long id, String type, String title, String content, String icon) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.type = type;
    this.icon = icon;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}

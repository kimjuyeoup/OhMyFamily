package com.example.demo.domain.Question.dto;

public class AnswerDto {
  private Long id;
  private String title;
  private String answer;
  private String icon;

  // AnswerDto 생성자
  public AnswerDto(Long id, String title, String answer, String icon) {
    this.id = id;
    this.title = title;
    this.answer = answer;
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

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}

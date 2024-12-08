package com.example.demo.domain.Question.dto;

public class ResultDto {
  private Long id;
  private Boolean isCorrect;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
  }
}

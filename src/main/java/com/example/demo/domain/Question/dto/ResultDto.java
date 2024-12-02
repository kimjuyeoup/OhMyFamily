package com.example.demo.domain.Question.dto;

public class ResultDto {
  private Long id;
  private Boolean isCorrect;

  // id 필드에 대한 getter와 setter
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  // isCorrect 필드에 대한 getter와 setter
  public Boolean getIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
  }
}

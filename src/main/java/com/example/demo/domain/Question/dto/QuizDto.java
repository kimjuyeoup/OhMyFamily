package com.example.demo.domain.Question.dto;

public class QuizDto {
  private Long quizid;

  public QuizDto(Long quizid) {
    this.quizid = quizid;
  }

  public Long getQuizid() {
    return quizid;
  }

  public void setQuizid(Long quizid) {
    this.quizid = quizid;
  }
}

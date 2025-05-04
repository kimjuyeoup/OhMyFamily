package com.example.demo.domain.Question.dto;

public class QuizDto {
  private String quizid;

  public QuizDto(String quizid) {
    this.quizid = quizid;
  }

  public String getQuizid() {
    return quizid;
  }

  public void setQuizid(String quizid) {
    this.quizid = quizid;
  }
}

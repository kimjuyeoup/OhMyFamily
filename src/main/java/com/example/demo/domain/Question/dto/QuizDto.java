package com.example.demo.domain.Question.dto;

public class QuizDto {
  private int quizid;

  public QuizDto(int quizid) {
    this.quizid = quizid;
  }

  public int getQuizid() {
    return quizid;
  }

  public void setQuizid(int quizid) {
    this.quizid = quizid;
  }
}

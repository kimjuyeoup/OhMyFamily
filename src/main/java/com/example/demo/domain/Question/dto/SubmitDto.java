package com.example.demo.domain.Question.dto;

import java.util.List;

public class SubmitDto {
  private Long id;
  private String name;
  private List<String> answer;
  private int quizid;

  public SubmitDto(String name, List<String> answer) {
    this.name = name;
    this.answer = answer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getAnswer() {
    return answer;
  }

  public void setAnswer(List<String> answer) {
    this.answer = answer;
  }

  public int getQuizid() {
    return quizid;
  }

  public void setQuizid(int quizid) {
    this.quizid = quizid;
  }
}

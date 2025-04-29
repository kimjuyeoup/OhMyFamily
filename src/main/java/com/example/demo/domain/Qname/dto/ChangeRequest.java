package com.example.demo.domain.Qname.dto;

public class ChangeRequest {
  private String name;
  private int quizid;

  public ChangeRequest() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuizid() {
    return quizid;
  }

  public void setQuizid(int quizid) {
    this.quizid = quizid;
  }
}

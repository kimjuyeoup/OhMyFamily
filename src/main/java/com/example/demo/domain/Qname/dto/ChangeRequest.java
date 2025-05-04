package com.example.demo.domain.Qname.dto;

public class ChangeRequest {
  private String name;
  private String quizid;

  public ChangeRequest() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQuizid() {
    return quizid;
  }

  public void setQuizid(String quizid) {
    this.quizid = quizid;
  }
}

package com.example.demo.domain.Question.dto;

import java.util.List;

public class SubmitDto {
  private Long id;
  private String name;
  private List<String> answer;
  private int quizid;
  private String image;

  public SubmitDto(String name, List<String> answer, String image) {
    this.name = name;
    this.answer = answer;
    this.image = image;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}

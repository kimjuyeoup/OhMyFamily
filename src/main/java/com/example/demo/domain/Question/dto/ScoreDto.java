package com.example.demo.domain.Question.dto;

import java.util.List;

public class ScoreDto {
  private List<ResultDto> result;
  private Long score;
  private String quizid;

  public ScoreDto(Long score, List<ResultDto> result, String quizid) {
    this.score = score;
    this.result = result;
    this.quizid = quizid;
  }

  public List<ResultDto> getResult() {
    return result;
  }

  public void setResult(List<ResultDto> result) {
    this.result = result;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public String getQuizid() {
    return quizid;
  }

  public void setQuizid(String quizid) {
    this.quizid = quizid;
  }
}

package com.example.demo.domain.Question.dto;

import java.util.List;

public class ScoreDto {
  private List<ResultDto> result;
  private Long score;
  private int quizid;

  public ScoreDto(Long score, List<ResultDto> result, int quizid) {
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

  public int getQuizid() {
    return quizid;
  }

  public void setQuizid(int quizid) {
    this.quizid = quizid;
  }
}

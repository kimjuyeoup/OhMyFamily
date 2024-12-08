package com.example.demo.domain.Question.dto;

import java.util.List;

public class ScoreDto {
  private List<ResultDto> result;
  private String nickname;
  private Long score;

  public ScoreDto(Long score, String nickname, List<ResultDto> result) {
    this.score = score;
    this.nickname = nickname;
    this.result = result;
  }

  public List<ResultDto> getResult() {
    return result;
  }

  public void setResult(List<ResultDto> result) {
    this.result = result;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }
}

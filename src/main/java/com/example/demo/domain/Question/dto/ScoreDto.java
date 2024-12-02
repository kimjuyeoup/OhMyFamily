package com.example.demo.domain.Question.dto;

import java.util.List;

public class ScoreDto {
  private List<ResultDto> result;
  private String image;
  private String nickname;
  private Long score;
  private String icon;

  public ScoreDto(Long score, String icon, String nickname) {
    this.score = score;
    this.nickname = nickname;
    this.icon = icon;
  }

  public List<ResultDto> getResult() {
    return result;
  }

  public void setResult(List<ResultDto> result) {
    this.result = result;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}

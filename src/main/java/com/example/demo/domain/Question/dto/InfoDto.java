package com.example.demo.domain.Question.dto;

public class InfoDto {
  private String kakao_nickname;
  private String nickname;

  public InfoDto(String kakao_nickname, String nickname) {
    this.kakao_nickname = kakao_nickname;
    this.nickname = nickname;
  }

  public String getKakao_nickname() {
    return kakao_nickname;
  }

  public void setKakao_nickname(String kakao_nickname) {
    this.kakao_nickname = kakao_nickname;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}

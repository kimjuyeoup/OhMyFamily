package com.example.demo.global.kakao;

import com.example.demo.global.oAuth.OAuthInfoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoInfoResponse implements OAuthInfoResponse {

  @JsonProperty("id")
  private Long kakaoId;

  @JsonProperty("kakao_account")
  private KakaoAccount kakaoAccount;

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  static class KakaoAccount {
    private KakaoProfile profile;
  }

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  static class KakaoProfile {
    private String nickname;
  }

  @Override
  public String getKakaoNickname() {
    return kakaoAccount.profile.nickname;
  }
}

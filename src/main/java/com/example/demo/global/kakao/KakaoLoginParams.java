package com.example.demo.global.kakao;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.global.oAuth.OAuthLoginParams;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {

  private String authorizationCode;

  @Override
  public MultiValueMap<String, String> makeBody() {
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("code", authorizationCode);
    return body;
  }
}

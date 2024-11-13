package com.example.demo.global.oAuth;

import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
  MultiValueMap<String, String> makeBody();
}

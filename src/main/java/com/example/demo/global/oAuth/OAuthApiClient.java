package com.example.demo.global.oAuth;

public interface OAuthApiClient {

  String requestAccessToken(OAuthLoginParams params);

  OAuthInfoResponse requestOauthInfo(String accessToken);
}

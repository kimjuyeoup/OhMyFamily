package com.example.demo.global.oAuth;

import com.example.demo.global.kakao.KakaoReissueParams;

public interface OAuthApiClient {

  String requestAccessToken(OAuthLoginParams params);

  String reissueAccessToken(KakaoReissueParams params);

  OAuthInfoResponse requestOauthInfo(String accessToken);
}

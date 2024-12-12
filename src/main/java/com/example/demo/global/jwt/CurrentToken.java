package com.example.demo.global.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentToken {

  public static Long getCurrentMemberId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null || !authentication.isAuthenticated()) {
      throw new IllegalStateException("인증되지 않은 사용자");
    }

    String userId = authentication.getPrincipal().toString();
    return Long.valueOf(userId);
  }
}

package com.example.demo.global.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.GlobalException;
import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtAuthProvider;
  private final UserDetailsService userDetailsService;
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  private final String[] excludedUrls = {
    "/api/v1/member/kakao", "/api/v1/member/reissue", "/kakao/callback", "/api/user", "/api/answer"
  };

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String requestUri = request.getRequestURI();

    // 검사 제외 URL인지 확인
    for (String excludedUrl : excludedUrls) {
      if (antPathMatcher.match(excludedUrl, requestUri)) {
        filterChain.doFilter(request, response);
        return; // 검사 제외
      }
    }

    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader != null) {
      String token = authorizationHeader;
      if (jwtAuthProvider.isTokenValid(token)) {
        Long userId = jwtAuthProvider.getMemberIdFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString());
        if (userDetails != null) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, "", userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
          throw new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER);
        }
      } else {
        throw new GlobalException(GlobalErrorCode.INVALID_TOKEN);
      }
    }
    filterChain.doFilter(request, response);
  }
}

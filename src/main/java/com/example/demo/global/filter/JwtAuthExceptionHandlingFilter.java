package com.example.demo.global.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.global.exception.BaseResponse;
import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.TokenException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthExceptionHandlingFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (TokenException e) {
      response.setContentType("application/json; charset=UTF-8");
      response.setStatus(HttpStatus.UNAUTHORIZED.value());

      GlobalErrorCode code = e.getErrorCode();

      BaseResponse<Object> errorResponse =
          BaseResponse.onFailure(GlobalErrorCode.INVALID_TOKEN, null);

      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(response.getOutputStream(), errorResponse);
    }
  }
}

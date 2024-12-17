package com.example.demo.global.handler;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.demo.global.exception.BaseResponse;
import com.example.demo.global.exception.GlobalErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    response.setContentType("application/json; charset=UTF-8");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    BaseResponse<Object> errorResponse =
        BaseResponse.onFailure(GlobalErrorCode.NOT_FOUND_MEMBER, null);

    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), errorResponse);

    response.getOutputStream().flush();
    response.getOutputStream().close();
  }
}

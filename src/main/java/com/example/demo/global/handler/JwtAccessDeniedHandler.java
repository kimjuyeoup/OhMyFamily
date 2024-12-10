package com.example.demo.global.handler;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.demo.global.exception.BaseResponse;
import com.example.demo.global.exception.GlobalErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException {
    System.out.println("10");
    response.setContentType("application/json; charset=UTF-8");
    response.setStatus(HttpStatus.FORBIDDEN.value());

    System.out.println("Access denied" + request.getRequestURI());
    accessDeniedException.printStackTrace();

    BaseResponse<Object> errorResponse =
        BaseResponse.onFailure(GlobalErrorCode.NOT_FOUND_MEMBER, null);

    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), errorResponse);
  }
}

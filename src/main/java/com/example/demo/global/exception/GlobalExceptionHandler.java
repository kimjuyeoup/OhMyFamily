package com.example.demo.global.exception;

import java.security.SignatureException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {GlobalException.class})
  protected BaseResponse handleCustomException(GlobalException e) {
    log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
    return BaseResponse.onFailure(e.getErrorCode(), null);
  }

  @ExceptionHandler(value = {TokenException.class})
  protected BaseResponse handleTokenException(TokenException e) {
    log.error("handleTokenException throw TokenException : {}", e.getErrorCode());
    return BaseResponse.onFailure(e.getErrorCode(), null);
  }

  @ExceptionHandler(value = {SignatureException.class})
  protected BaseResponse handleSignatureException(SignatureException e) {
    log.error("handleSignatureException: JWT signature validation failed - {}", e.getMessage());
    return BaseResponse.onFailure(GlobalErrorCode.INVALID_TOKEN, null);
  }
}

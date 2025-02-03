package com.example.demo.global.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "divisionCode", "message", "result"})
public class BaseResponse<T> {

  @JsonProperty("isSuccess")
  private Boolean isSuccess;

  private int code;
  private String divisionCode;
  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  // 성공한 경우 응답 생성
  public static <T> BaseResponse<T> onSuccess(T data) {
    return new BaseResponse<>(true, 200, "d-000", "요청에 성공하였습니다.", data);
  }

  public static <T> BaseResponse<List<T>> onSuccess(List<T> data) {
    return new BaseResponse<>(true, 200, "d-000", "요청에 성공하였습니다.", data);
  }

  public static <T> BaseResponse<T> onSuccess(GlobalErrorCode code, T data) {
    return new BaseResponse<>(true, code.getHttpStatus().value(), "d-000", code.getMessage(), data);
  }

  // 실패한 경우 응답 생성
  public static <T> BaseResponse<T> onFailure(GlobalErrorCode code, T data) {
    return new BaseResponse<>(
        false, code.getHttpStatus().value(), code.getDivisionCode(), code.getMessage(), data);
  }
}

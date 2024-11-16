package com.example.demo.domain.quiz.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.quiz.dto.request.SetQuizNicknameRequest;
import com.example.demo.domain.quiz.dto.response.SetQuizNicknameResponse;
import com.example.demo.domain.quiz.service.QuizCommandService;
import com.example.demo.global.exception.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuizController {

  private final QuizCommandService quizCommandService;

  @PostMapping("/nickname")
  public BaseResponse<SetQuizNicknameResponse> setQuizNickname(
      @RequestHeader("Authorization") String accessToken,
      @RequestBody SetQuizNicknameRequest request) {
    return BaseResponse.onSuccess(quizCommandService.setQuizNickname(accessToken, request));
  }
}

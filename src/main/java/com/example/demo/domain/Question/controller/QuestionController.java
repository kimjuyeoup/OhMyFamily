package com.example.demo.domain.Question.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Question.dto.ScoreDto;
import com.example.demo.domain.Question.dto.SubmitDto;
import com.example.demo.domain.Question.service.QuestionService;
import com.example.demo.domain.Question.service.QuestionServices;
import com.example.demo.domain.SetQuestion.dto.SetQuestionDto;
import com.example.demo.global.exception.BaseResponse;
import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {
  private final QuestionService questionService;
  private final QuestionServices questionServices;
  private final JwtTokenProvider jwtTokenProvider;

  @GetMapping("/question")
  public BaseResponse<List<SetQuestionDto>> getAllQuestions(@RequestParam String name, String id) {
    return BaseResponse.onSuccess(questionService.getQuestionByName(name, id));
  }

  @GetMapping("/answer")
  public BaseResponse<Map<String, Object>> getAnswerByName(@RequestParam String name, String id) {
    Map<String, Object> response = questionService.getAnswerByName(name, id);
    return BaseResponse.onSuccess(response);
  }

  @PostMapping("/score")
  public BaseResponse<ScoreDto> updateScore(@RequestBody ScoreDto request) {
    ScoreDto updatedScore = questionServices.updateScoreByNickname(request);
    return BaseResponse.onSuccess(updatedScore);
  }

  @PostMapping("/submit")
  public BaseResponse<SubmitDto> submitQuestion(@ModelAttribute SubmitDto request) {
    SubmitDto submit = questionServices.updateSubmitByNickname(request);
    return BaseResponse.onSuccess(submit);
  }

  // @RequestBody 대신 @ModelAttribute 사용하면 폼데이터를 받는다.
}

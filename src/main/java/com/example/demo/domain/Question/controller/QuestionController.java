package com.example.demo.domain.Question.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Question.dto.InfoDto;
import com.example.demo.domain.Question.dto.QuizDto;
import com.example.demo.domain.Question.dto.ScoreDto;
import com.example.demo.domain.Question.dto.SubmitDto;
import com.example.demo.domain.Question.dto.request.ChangeRequest;
import com.example.demo.domain.Question.service.QuestionService;
import com.example.demo.domain.Question.service.QuestionServices;
import com.example.demo.domain.SetQuestion.dto.SetQuestionDto;
import com.example.demo.domain.member.service.MemberCommandService;
import com.example.demo.domain.member.service.MemberQueryService;
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
  private final MemberCommandService memberCommandService;
  private final MemberQueryService memberQueryService;

  @GetMapping("/question")
  public BaseResponse<List<SetQuestionDto>> getAllQuestions(@RequestParam String name, String id) {
    return BaseResponse.onSuccess(questionService.getQuestionByName(name, id));
  }

  @GetMapping("/answer")
  public BaseResponse<Map<String, Object>> getAnswerByName(@RequestParam int quizid) {
    Map<String, Object> response = questionService.getAnswerByName(quizid);
    return BaseResponse.onSuccess(response);
  }

  @GetMapping("/user")
  public BaseResponse<InfoDto> getUserInfo(@RequestParam int quizid) {
    return BaseResponse.onSuccess(questionService.getInfo(quizid));
  }

  @PostMapping("/change")
  public BaseResponse<String> getChangeByName(@RequestBody ChangeRequest request) {
    return BaseResponse.onSuccess(
        questionServices.getChangeByName(request.getName(), request.getQuizid()));
  }

  @PostMapping("/score")
  public BaseResponse<ScoreDto> updateScore(@RequestBody ScoreDto request) {
    ScoreDto updatedScore = questionServices.updateScoreByNickname(request);
    return BaseResponse.onSuccess(updatedScore);
  }

  @PostMapping("/submit")
  public BaseResponse<QuizDto> submitQuestion(
      @RequestBody SubmitDto request, @RequestHeader("Authorization") String accessToken) {
    Long memberid = memberQueryService.getMemberId(accessToken);
    QuizDto quizDto = questionServices.updateSubmitByNickname(request, memberid);
    return BaseResponse.onSuccess(quizDto);
  }
}

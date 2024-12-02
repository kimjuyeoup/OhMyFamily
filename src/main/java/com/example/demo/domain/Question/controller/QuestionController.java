package com.example.demo.domain.Question.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Question.dto.QuestionDto;
import com.example.demo.domain.Question.dto.ScoreDto;
import com.example.demo.domain.Question.dto.SubmitDto;
import com.example.demo.domain.Question.service.QuestionService;
import com.example.demo.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {
  private final QuestionService questionService;
  private final MemberRepository memberRepository;

  @GetMapping("/question")
  public List<QuestionDto> getAllQuestions(@RequestParam String name) {
    return questionService.getQuestionsByName(name);
  }

  @GetMapping("/answer")
  public ResponseEntity<Map<String, Object>> getAnswerByName(@RequestParam String name) {
    Map<String, Object> response = questionService.getAnswerByName(name);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/score")
  public ResponseEntity<ScoreDto> updateScore(@RequestBody ScoreDto request) {
    ScoreDto updatedScore = questionService.updateScoreByNickname(request);
    return ResponseEntity.ok(updatedScore);
  }

  @PostMapping("/submit")
  public ResponseEntity<SubmitDto> submitQuestion(@ModelAttribute SubmitDto request) {
    SubmitDto submit = questionService.updateSubmitByNickname(request);
    return ResponseEntity.ok(submit);
  }

  // @RequestBody 대신 @ModelAttribute 사용하면 폼데이터를 받는다.
}

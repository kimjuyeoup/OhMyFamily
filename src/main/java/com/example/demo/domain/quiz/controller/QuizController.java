package com.example.demo.domain.quiz.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.quiz.dto.QuizDto;
import com.example.demo.domain.quiz.dto.request.SetQuizNicknameRequest;
import com.example.demo.domain.quiz.dto.response.SetQuizNicknameResponse;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.domain.quiz.service.QuizCommandService;
import com.example.demo.global.exception.BaseResponse;
import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.GlobalException;
import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuizController {

  private final QuizRepository quizRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;
  private final QuizCommandService quizCommandService;

  @PostMapping("/nickname")
  public BaseResponse<SetQuizNicknameResponse> setQuizNickname(
      @RequestHeader("Authorization") String accessToken,
      @RequestBody SetQuizNicknameRequest request) {
    return BaseResponse.onSuccess(quizCommandService.setQuizNickname(accessToken, request));
  }

  @GetMapping("/search")
  public ResponseEntity<List<QuizDto>> getData() {
    /*public ResponseEntity<List<Quiz>> getData(@RequestHeader("Authorization") String token) {
      String accessToken = token.replace("Bearer ", "");
    */
    // Long userId = Long.parseLong(jwtTokenProvider.extractSubject(accessToken));
    Long userId = 1L;
    Member member =
        memberRepository
            .findById(userId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    List<QuizDto> data =
        quizRepository.findByMember(member).stream()
            .map(quiz -> new QuizDto(quiz.getNickname(), quiz.getScore()))
            .collect(Collectors.toList());

    return ResponseEntity.ok(data);
  }
}

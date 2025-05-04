package com.example.demo.domain.quiz.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Question.dto.response.CheckedAnswerResponseDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.service.MemberQueryService;
import com.example.demo.domain.quiz.dto.QuizDto;
import com.example.demo.domain.quiz.dto.request.SetQuizNicknameRequest;
import com.example.demo.domain.quiz.dto.response.SetQuizNicknameResponse;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.domain.quiz.service.QuizCommandService;
import com.example.demo.global.encrypt.EncryptService;
import com.example.demo.global.exception.BaseResponse;
import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuizController {

  private final QuizRepository quizRepository;
  private final MemberRepository memberRepository;
  private final QuizCommandService quizCommandService;
  private final MemberQueryService memberQueryService;
  private final EncryptService encryptService;

  @PostMapping("/nickname")
  public BaseResponse<SetQuizNicknameResponse> setQuizNickname(
      @RequestHeader("Authorization") String accessToken,
      @RequestBody SetQuizNicknameRequest request) {
    return BaseResponse.onSuccess(quizCommandService.setQuizNickname(accessToken, request));
  }

  @GetMapping("/search")
  public BaseResponse<List<QuizDto>> getData(@RequestHeader("Authorization") String accessToken) {
    Long memberId = memberQueryService.getMemberId(accessToken);
    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));
    List<QuizDto> data =
        quizRepository.findByMember(member).stream()
            .filter(quiz -> quiz.getScore() != null)
            .map(
                quiz -> {
                  String value = quizCommandService.getIcon(quiz.getScore());
                  Long Score = quizCommandService.getChange(quiz.getScore());
                  String encryptedId;
                  try {
                    encryptedId = encryptService.encrypt(quiz.getId());
                  } catch (Exception e) {
                    throw new RuntimeException("Failed to encrypt quiz ID", e);
                  }
                  return new QuizDto(
                      encryptedId, quiz.getCheck(), quiz.getNickname(), Score, value);
                })
            .collect(Collectors.toList());
    return BaseResponse.onSuccess(data);
  }

  @GetMapping("/check/answer/{quizId}")
  public BaseResponse<List<CheckedAnswerResponseDto>> getCheckedQuestion(
      @PathVariable String quizId) throws Exception {
    return BaseResponse.onSuccess(
        quizCommandService.getCheckedQuestion(encryptService.decrypt(quizId)));
  }

  @GetMapping("/check/{quizId}")
  public BaseResponse<Boolean> getIsChecked(@PathVariable String quizId) throws Exception {
    return BaseResponse.onSuccess(quizCommandService.getIsChecked(encryptService.decrypt(quizId)));
  }
}

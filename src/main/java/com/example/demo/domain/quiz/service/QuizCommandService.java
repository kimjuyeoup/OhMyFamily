package com.example.demo.domain.quiz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.quiz.converter.QuizConverter;
import com.example.demo.domain.quiz.dto.request.SetQuizNicknameRequest;
import com.example.demo.domain.quiz.dto.response.SetQuizNicknameResponse;
import com.example.demo.domain.quiz.entity.Quiz;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.GlobalException;
import com.example.demo.global.oAuth.AuthTokensGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizCommandService {

  private final QuizRepository quizRepository;
  private final AuthTokensGenerator authTokensGenerator;

  private final MemberRepository memberRepository;

  public SetQuizNicknameResponse setQuizNickname(
      String accessToken, SetQuizNicknameRequest request) {

    Long memberId = authTokensGenerator.extractMemberId(accessToken);

    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    Quiz quiz = quizRepository.save(QuizConverter.toQuiz(request, member));

    return QuizConverter.toSetQuizNicknameResponse(quiz);
  }
}

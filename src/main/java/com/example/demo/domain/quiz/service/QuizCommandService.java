package com.example.demo.domain.quiz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Question.repository.QuestionRepository;
import com.example.demo.domain.card.repository.CardRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.quiz.converter.QuizConverter;
import com.example.demo.domain.quiz.dto.request.SetQuizNicknameRequest;
import com.example.demo.domain.quiz.dto.response.SetQuizNicknameResponse;
import com.example.demo.domain.quiz.entity.Quiz;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.global.exception.GlobalErrorCode;
import com.example.demo.global.exception.GlobalException;
import com.example.demo.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizCommandService {

  private final QuizRepository quizRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;
  private final QuestionRepository questionRepository;
  private final CardRepository cardRepository;

  public SetQuizNicknameResponse setQuizNickname(
      String accessToken, SetQuizNicknameRequest request) {

    Long memberId = jwtTokenProvider.getMemberIdFromToken(accessToken);

    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_MEMBER));

    Quiz quiz = quizRepository.save(QuizConverter.toQuiz(request, member));

    return QuizConverter.toSetQuizNicknameResponse(quiz);
  }

  public String getIcon(Long a) {
    Long ChangeValue = 0L;
    if (a >= 0 && a <= 20) {
      ChangeValue = 20L;
    } else if (a > 20 && a <= 40) {
      ChangeValue = 40L;
    } else if (a > 40 && a <= 60) {
      ChangeValue = 60L;
    } else if (a > 60 && a <= 80) {
      ChangeValue = 80L;
    } else if (a > 80 && a <= 90) {
      ChangeValue = 90L;
    } else if (a > 90 && a <= 100) {
      ChangeValue = 100L;
    }

    if (questionRepository.findAnswerByQuizid11(a.intValue()) != null) {
      String icon = questionRepository.findAnswerByQuizid11(a.intValue());
      return icon;
    } else {
      String icon =
          cardRepository.findIconByScore(quizRepository.findScoreByQuizid(ChangeValue.intValue()));
      return icon;
    }
  }
}

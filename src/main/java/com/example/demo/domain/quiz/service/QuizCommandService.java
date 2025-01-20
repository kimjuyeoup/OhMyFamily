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
    System.out.println(questionRepository.findFirstAnswerByQuizid11(a.intValue()) + "a의 값 : " + a);
    if (questionRepository.findFirstAnswerByQuizid11(a.intValue()) != null) {
      String icon = questionRepository.findFirstAnswerByQuizid11(a.intValue());
      return icon;
    } else {
      String icon = cardRepository.findIconByScore(quizRepository.findScoreByQuizid(a.intValue()));
      return icon;
    }
  }
}

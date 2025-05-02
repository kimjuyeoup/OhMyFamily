package com.example.demo.domain.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Question.dto.response.CheckedAnswerResponseDto;
import com.example.demo.domain.Question.repository.QuestionRepository;
import com.example.demo.domain.SetQuestion.entity.SetQuestion;
import com.example.demo.domain.SetQuestion.repository.SetQuestionRepository;
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
  private final SetQuestionRepository setQuestionRepository;

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
    if (questionRepository.findAnswerByQuizid11(a.intValue()) != null) {
      String icon = questionRepository.findAnswerByQuizid11(a.intValue());
      return icon;
    } else {
      String icon = cardRepository.findIconByScore(getChange(a));
      return icon;
    }
  }

  public Long getChange(Long a) {
    if (a >= 0 && a <= 20) {
      a = 20L;
    } else if (a > 20 && a <= 40) {
      a = 40L;
    } else if (a > 40 && a <= 60) {
      a = 60L;
    } else if (a > 60 && a <= 80) {
      a = 80L;
    } else if (a > 80 && a <= 90) {
      a = 90L;
    } else if (a > 90 && a <= 100) {
      a = 100L;
    }
    return a;
  }

  public List<CheckedAnswerResponseDto> getCheckedQuestion(Long questId) {
    Quiz quiz =
        quizRepository
            .findById(questId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_QUIZ));

    if (!quiz.getCheck()) {
      throw new GlobalException(GlobalErrorCode.NOT_MARKED_QUIZ);
    }

    List<CheckedAnswerResponseDto> checkedAnswerResponseDtoList =
        questionRepository.findAnswerByQuizid(questId.intValue()).stream()
            .map(
                question -> {
                  SetQuestion setQuestion =
                      setQuestionRepository
                          .findById(question.getSetId())
                          .orElseThrow(
                              () -> new GlobalException(GlobalErrorCode.NOT_FOUND_SET_QUESTION));

                  return new CheckedAnswerResponseDto(
                      setQuestion.getContent(),
                      setQuestion.getIcon(),
                      setQuestion.getTitle(question.getName()),
                      setQuestion.getType(),
                      question.getAnswer(),
                      question.getName(),
                      question.getIsAnswer());
                })
            .toList();

    return checkedAnswerResponseDtoList;
  }

  public Boolean getIsChecked(Long quizId) {
    Quiz quiz =
        quizRepository
            .findById(quizId)
            .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_FOUND_QUIZ));
    return quiz.getScore() != null;
  }
}

package com.example.demo.domain.Question.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Question.dto.ResultDto;
import com.example.demo.domain.Question.dto.ScoreDto;
import com.example.demo.domain.Question.dto.SubmitDto;
import com.example.demo.domain.Question.entity.QuestionEntity;
import com.example.demo.domain.Question.repository.QuestionRepository;
import com.example.demo.domain.SetQuestion.entity.SetQuestion;
import com.example.demo.domain.SetQuestion.repository.SetQuestionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.quiz.entity.Quiz;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.global.jwt.CurrentToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServices {

  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;
  private final SetQuestionRepository setQuestionRepository;
  private final MemberRepository memberRepository;
  private final CurrentToken currentToken;

  public ScoreDto updateScoreByNickname(ScoreDto scoreDto) {

    List<QuestionEntity> questions = questionRepository.findAnswerByQuizid(scoreDto.getQuizid());
    if (questions.isEmpty()) {
      throw new IllegalArgumentException("Questions not found for user: " + scoreDto.getNickname());
    }

    for (int i = 0; i < scoreDto.getResult().size(); i++) {
      ResultDto resultDto = scoreDto.getResult().get(i);

      if (i < questions.size()) {
        QuestionEntity question = questions.get(i);
        question.setIsAnswer(resultDto.getIsCorrect());
        questionRepository.save(question);
      }
    }
    Set<Long> setIds = new HashSet<>();
    for (QuestionEntity question : questions) {
      if (Boolean.TRUE.equals(question.getIsAnswer())) {
        setIds.add(question.getSetId());
      }
    }

    List<SetQuestion> setQuestions = setQuestionRepository.findAllById(setIds);
    Long totalScore = setQuestions.stream().mapToLong(SetQuestion::getScore).sum();

    Quiz quiz =
        Quiz.builder()
            .nickname(scoreDto.getNickname())
            .score(totalScore)
            .check(false)
            .id((long) scoreDto.getQuizid())
            .build();

    quizRepository.save(quiz);
    return new ScoreDto(10L, scoreDto.getNickname(), scoreDto.getResult(), scoreDto.getQuizid());
  }

  public SubmitDto updateSubmitByNickname(SubmitDto submitDto) {

    Long memberId = currentToken.getCurrentMemberId();
    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    List<String> answers = submitDto.getAnswer();
    List<SetQuestion> setQuestions = setQuestionRepository.findAll();
    List<QuestionEntity> questions = new ArrayList<>();
    int maxnumber = questionRepository.findQuizId();
    int number = maxnumber + 1;
    for (int i = 0; i < answers.size(); i++) {
      QuestionEntity question = new QuestionEntity();
      question.setName(submitDto.getName());
      question.setAnswer(answers.get(i));
      question.setMember(member);
      if (i < setQuestions.size()) {
        SetQuestion setQuestion = setQuestions.get(i);
        question.setSetId(setQuestion.getId());
      }
      question.setQuizid(number);
      questions.add(question);
    }
    questionRepository.saveAll(questions);
    submitDto.setQuizid(number);

    return submitDto;
  }
}

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

  public ScoreDto updateScoreByNickname(ScoreDto scoreDto) {

    List<QuestionEntity> questions = questionRepository.findByName(scoreDto.getNickname());
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

    Quiz quiz = new Quiz();
    quiz.setNickname(scoreDto.getNickname());
    quiz.setScore(totalScore);
    quiz.setCheck(false);

    quizRepository.save(quiz);
    return new ScoreDto(10L, scoreDto.getNickname(), scoreDto.getResult());
  }

  public SubmitDto updateSubmitByNickname(SubmitDto submitDto) {

    Long memberId = CurrentToken.getCurrentMemberId();
    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    List<String> answers = submitDto.getAnswer();
    List<SetQuestion> setQuestions = setQuestionRepository.findAll();
    List<QuestionEntity> questions = new ArrayList<>();
    for (int i = 0; i < answers.size(); i++) {
      QuestionEntity question = new QuestionEntity();
      question.setName(submitDto.getName());
      question.setAnswer(answers.get(i));
      question.setMember(member);
      if (i < setQuestions.size()) {
        SetQuestion setQuestion = setQuestions.get(i);
        question.setSetId(setQuestion.getId());
      }
      questions.add(question);
    }
    questionRepository.saveAll(questions);

    return submitDto;
  }
}

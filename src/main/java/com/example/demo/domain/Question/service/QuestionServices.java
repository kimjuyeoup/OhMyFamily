package com.example.demo.domain.Question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Question.dto.ResultDto;
import com.example.demo.domain.Question.dto.ScoreDto;
import com.example.demo.domain.Question.dto.SubmitDto;
import com.example.demo.domain.Question.entity.QuestionEntity;
import com.example.demo.domain.Question.repository.QuestionRepository;
import com.example.demo.domain.quiz.entity.Quiz;
import com.example.demo.domain.quiz.repository.QuizRepository;
import com.example.demo.domain.s3.S3UploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServices {

  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;
  private final S3UploadService s3UploadService;

  public ScoreDto updateScoreByNickname(ScoreDto scoreDto) {

    List<QuestionEntity> questions = questionRepository.findByName(scoreDto.getNickname());
    if (questions.isEmpty()) {
      throw new IllegalArgumentException("Questions not found for user: " + scoreDto.getNickname());
    }

    for (ResultDto updatedResult : scoreDto.getResult()) {
      Optional<QuestionEntity> optionalQuestion =
          questionRepository.findById(updatedResult.getId());

      if (optionalQuestion.isPresent()) {
        QuestionEntity question = optionalQuestion.get();
        question.setIsAnswer(updatedResult.getIsCorrect());
        questionRepository.save(question);
      }
    }
    Long score = questionRepository.findAllScore();

    Quiz quiz = new Quiz();
    quiz.setNickname(scoreDto.getNickname());
    quiz.setScore(score);
    quiz.setCheck(false);

    quizRepository.save(quiz);

    return new ScoreDto(score, scoreDto.getNickname());
  }

  public SubmitDto updateSubmitByNickname(SubmitDto submitDto) {
    if (submitDto.getImage() != null && !submitDto.getImage().isEmpty()) {
      s3UploadService.uploadFile(submitDto.getImage());
    }
    List<QuestionEntity> questions = questionRepository.findByName(submitDto.getName());
    List<String> answers = submitDto.getAnswer();
    for (int i = 0; i < questions.size(); i++) {
      QuestionEntity question = questions.get(i);
      if (i < answers.size()) {
        question.setAnswer(answers.get(i));
      }
    }
    questionRepository.saveAll(questions);
    return submitDto;
  }
}

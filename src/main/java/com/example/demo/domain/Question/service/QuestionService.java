package com.example.demo.domain.Question.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Question.dto.*;
import com.example.demo.domain.Question.dto.response.AnswerResponse;
import com.example.demo.domain.Question.entity.QuestionEntity;
import com.example.demo.domain.Question.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

  private final QuestionRepository questionRepository;

  public List<QuestionDto> getQuestionsByName(String name) {

    List<QuestionEntity> existingQuestions = questionRepository.findByName(name);
    if (!existingQuestions.isEmpty()) {
      questionRepository.deleteAll(existingQuestions);
    }
    for (QuestionEnum questionEnum : QuestionEnum.values()) {
      QuestionEntity questionEntity =
          new QuestionEntity(
              questionEnum.getType(),
              questionEnum.getTitle(name),
              questionEnum.getIsAnswer(),
              questionEnum.getAnswer(),
              questionEnum.getContent(name),
              questionEnum.getIcon(),
              questionEnum.getScore(),
              name,
              questionEnum.getNumber());
      questionRepository.save(questionEntity);
    }

    List<QuestionEntity> questions = questionRepository.findByName(name);
    return questions.stream()
        .map(
            question -> {
              Object content =
                  question.getType().equals("select")
                      ? question.getContent().split(", ")
                      : question.getContent();

              return new QuestionDto(
                  question.getNumber(),
                  question.getType(),
                  question.getTitle(),
                  content,
                  question.getIcon());
            })
        .collect(Collectors.toList());
  }

  public Map<String, Object> getAnswerByName(String name) {
    List<QuestionEntity> questions = questionRepository.findAnswerByName(name);
    List<AnswerResponse> answers =
        questions.stream()
            .limit(10)
            .map(
                question ->
                    AnswerResponse.builder()
                        .id(question.getId())
                        .title(question.getTitle())
                        .answer(question.getAnswer())
                        .icon(question.getIcon())
                        .build())
            .collect(Collectors.toList());
    String imageUrl = questions.isEmpty() ? null : questions.get(questions.size() - 1).getIcon();

    Map<String, Object> result = new HashMap<>();
    result.put("data", answers);
    if (imageUrl != null && !imageUrl.isEmpty()) {
      result.put("imageUrl", imageUrl);
    }
    return result;
  }
}

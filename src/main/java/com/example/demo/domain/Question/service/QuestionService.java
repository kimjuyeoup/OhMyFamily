package com.example.demo.domain.Question.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Question.dto.response.AnswerResponse;
import com.example.demo.domain.Question.entity.QuestionEntity;
import com.example.demo.domain.Question.repository.QuestionRepository;
import com.example.demo.domain.SetQuestion.dto.SetQuestionDto;
import com.example.demo.domain.SetQuestion.entity.SetQuestion;
import com.example.demo.domain.SetQuestion.repository.SetQuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

  private final SetQuestionRepository setQuestionRepository;
  private final QuestionRepository questionRepository;

  public List<SetQuestionDto> getQuestionByName(String name) {

    List<SetQuestion> questions = setQuestionRepository.findAll();

    return questions.stream()
        .map(
            question ->
                new SetQuestionDto(
                    question.getId(),
                    question.getTitle(name),
                    question.getContentsFormatted(name),
                    question.getIcon(),
                    question.getType()))
        .collect(Collectors.toList());
  }

  public Map<String, Object> getAnswerByName(String name) {
    List<QuestionEntity> questions = questionRepository.findAnswerByName(name);

    List<AnswerResponse> answers =
        questions.stream()
            .map(
                question -> {
                  SetQuestion setQuestion = question.getSetQuestion();
                  String title = setQuestion != null ? setQuestion.getTitle(name) : null;
                  String icon = setQuestion != null ? setQuestion.getIcon() : null;

                  return AnswerResponse.builder()
                      .id(question.getId())
                      .answer(question.getAnswer())
                      .title(title)
                      .icon(icon)
                      .build();
                })
            .collect(Collectors.toList());

    Map<String, Object> result = new HashMap<>();
    result.put("data", answers);

    return result;
  }
}

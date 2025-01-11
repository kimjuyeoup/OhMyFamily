package com.example.demo.domain.SetQuestion.service;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.SetQuestion.entity.SetQuestion;
import com.example.demo.domain.SetQuestion.entity.SetQuestions;
import com.example.demo.domain.SetQuestion.repository.SetQuestionRepository;

@Service
public class SetQuestionService {

  @Autowired private SetQuestionRepository setQuestionRepository;

  @PostConstruct
  public void init() {
    if (setQuestionRepository.count() == 0) {
      for (SetQuestions setQuestions : SetQuestions.values()) {
        SetQuestion setQuestion = new SetQuestion();
        setQuestion.setContent(setQuestions.getContent());
        setQuestion.setTitle(setQuestions.getTitle());
        setQuestion.setIcon(setQuestions.getIcon());
        setQuestion.setScore(setQuestions.getScore());
        setQuestion.setType(setQuestions.getType());

        setQuestionRepository.save(setQuestion);
      }
    }
  }
}

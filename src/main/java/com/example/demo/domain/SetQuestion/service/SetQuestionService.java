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
    try {
      long count = setQuestionRepository.count();

      if (count > 0) {
        setQuestionRepository.deleteAll();
      }
      for (SetQuestions setQuestions : SetQuestions.values()) {
        SetQuestion setQuestion = new SetQuestion();
        setQuestion.setContent(setQuestions.getContent());
        setQuestion.setTitle(setQuestions.getTitle());
        setQuestion.setIcon(setQuestions.getIcon());
        setQuestion.setScore(setQuestions.getScore());
        setQuestion.setType(setQuestions.getType());

        setQuestionRepository.save(setQuestion);
      }

    } catch (Exception e) {
      System.err.println("❌ SetQuestion 초기화 중 에러 발생: " + e.getMessage());
      e.printStackTrace();
    }
  }
}

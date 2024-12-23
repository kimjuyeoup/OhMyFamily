package com.example.demo.domain.Question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Question.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

  List<QuestionEntity> findByName(String name);

  List<QuestionEntity> findAnswerByQuizid(int quiz_id);

  List<QuestionEntity> findBySetQuestionId(Long setId);

  @Query("SELECT COALESCE(MAX(q.quizid), 0) FROM QuestionEntity q")
  int findQuizId();
}

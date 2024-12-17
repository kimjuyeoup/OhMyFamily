package com.example.demo.domain.Question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Question.entity.QuestionEntity;

import io.lettuce.core.dynamic.annotation.Param;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

  List<QuestionEntity> findByName(String name);

  List<QuestionEntity> findAnswerByName(String name);

  List<QuestionEntity> findBySetQuestionId(Long setId);

  @Query("SELECT COALESCE(MAX(q.number),0) FROM QuestionEntity q WHERE q.name = :name")
  int findMaxNumber(@Param("name") String name);
}

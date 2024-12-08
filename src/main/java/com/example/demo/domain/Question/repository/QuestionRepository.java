package com.example.demo.domain.Question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Question.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

  List<QuestionEntity> findByName(String name);

  List<QuestionEntity> findAnswerByName(String name);

  /*@Query("SELECT SUM(q.score) FROM QuestionEntity q WHERE q.isAnswer = TRUE")
  Long findAllScore();*/
}

package com.example.demo.domain.Question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Question.entity.QuestionEntity;

import io.lettuce.core.dynamic.annotation.Param;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

  List<QuestionEntity> findByName(String name);

  List<QuestionEntity> findAnswerByQuizid(int quiz_id);

  List<QuestionEntity> findBySetQuestionId(Long setId);

  @Query("SELECT COALESCE(MAX(q.quizid), 0) FROM QuestionEntity q")
  int findQuizId();

  @Query(
      value = "SELECT q.name FROM question_entity q WHERE q.quizid = :quizid LIMIT 1",
      nativeQuery = true)
  List<String> findNameByQuizid(@Param("quizid") int quizid);

  @Query(
      value = "SELECT q.member_id FROM question_entity q WHERE q.quizid = :quizid LIMIT 1",
      nativeQuery = true)
  List<Long> findMemberByQuizid(@Param("quizid") int quizid);

  @Query(
      value =
          "SELECT q.answer FROM question_entity q WHERE q.quizid = :quizid AND q.set_question_id = 11 LIMIT 1",
      nativeQuery = true)
  String findAnswerByQuizid11(@Param("quizid") int quizid);
}

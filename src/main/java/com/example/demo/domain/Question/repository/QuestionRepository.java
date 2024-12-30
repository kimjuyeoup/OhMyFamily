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

  @Query("SELECT q.name FROM QuestionEntity q WHERE q.quizid = :quizid")
  String findNameByQuizid(@Param("quizid") int quizid);

  @Query("SELECT q.member.id FROM QuestionEntity q WHERE q.quizid = :quizid")
  Long findMemberByQuizid(@Param("quizid") int quizid);
}

package com.example.demo.domain.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
  List<Quiz> findByMember(Member member);

  @Query("SELECT q.score FROM Quiz q WHERE q.id = :quizid")
  Long findScoreByQuizid(@Param("quizid") Integer quizid);

  @Query("SELECT q.nickname FROM Quiz q WHERE q.id = :quizid")
  String findNameByQuizid(@Param("quizid") Integer quizid);

  Quiz findByQuizid(int quizid);

  @Modifying
  @Query("UPDATE Quiz q SET q.check = true WHERE q.id = :quizid")
  int updateCheck(@Param("quizid") Integer quizid);
}

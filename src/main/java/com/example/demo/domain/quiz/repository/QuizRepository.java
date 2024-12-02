package com.example.demo.domain.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
  List<Quiz> findByMember(Member member);
}

package com.example.demo.domain.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {}

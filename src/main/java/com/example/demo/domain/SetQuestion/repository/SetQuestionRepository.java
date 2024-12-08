package com.example.demo.domain.SetQuestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.SetQuestion.entity.SetQuestion;

@Repository
public interface SetQuestionRepository extends JpaRepository<SetQuestion, Long> {}

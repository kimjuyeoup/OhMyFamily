package com.example.demo.domain.Qname.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Qname.entity.QnameEntity;

public interface QnameRepository extends JpaRepository<QnameEntity, Long> {

  QnameEntity findFirstByQuizid(int quizid);

  @Query("SELECT q.name FROM QnameEntity q WHERE q.quizid = :quizid")
  Optional<String> findNameByQuizid(@Param("quizid") int quizid);
}

package com.example.demo.domain.card.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
  List<Card> findByScore(Long score);

  @Query("SELECT c.icon FROM Card c WHERE c.score = :score")
  String findIconByScore(@Param("score") Long score);
}

package com.example.demo.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {}

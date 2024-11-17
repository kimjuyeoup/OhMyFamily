package com.example.demo.domain.card.entity;

import jakarta.persistence.*;

import com.example.demo.domain.quiz.entity.Quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "card_id")
  private Long id;

  @Column(name = "score")
  private Integer score;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "title")
  private String title;

  @Column(name = "subtitle")
  private String subtitle;

  @Column(name = "content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz")
  private Quiz quiz;
}

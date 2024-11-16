package com.example.demo.domain.quiz.entity;

import jakarta.persistence.*;

import com.example.demo.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "quiz_id")
  private Long id;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "score")
  private Integer score;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member")
  private Member member;
}

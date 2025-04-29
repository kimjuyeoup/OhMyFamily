package com.example.demo.domain.Qname.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quizname")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int quizid;

  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuizid() {
    return quizid;
  }

  public void setQuizid(int quizid) {
    this.quizid = quizid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

package com.example.demo.domain.Question.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id")
  private Long id;

  @Column(name = "question_number")
  private Long number;

  @Column(name = "content")
  private String content;

  @Column(name = "answer")
  private String answer;

  @Column(name = "is_answer")
  private Boolean isAnswer;

  @Column(name = "title")
  private String title;

  @Column(name = "type")
  private String type;

  @Column(name = "score")
  private Long score;

  @Column(name = "name")
  private String name;

  @Column(name = "icon")
  private String icon;

  /*@ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_id")
  private Quiz quiz;*/

  public QuestionEntity(
      String type,
      String title,
      Boolean isAnswer,
      String answer,
      String content,
      String icon,
      Long score,
      String name,
      Long number) {
    this.type = type;
    this.title = title;
    this.content = content;
    this.icon = icon;
    this.name = name;
    this.isAnswer = isAnswer;
    this.score = score;
    this.answer = answer;
    this.number = number;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Boolean getIsAnswer() {
    return isAnswer;
  }

  public void setIsAnswer(Boolean answer) {
    isAnswer = answer;
  }
}

package com.example.demo.domain.Question.entity;

import jakarta.persistence.*;

import com.example.demo.domain.SetQuestion.entity.SetQuestion;
import com.example.demo.domain.member.entity.Member;

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

  @Column(name = "answer")
  private String answer;

  @Column(name = "is_answer")
  private Boolean isAnswer;

  @Column(name = "name")
  private String name;

  @Column(name = "quizid")
  private int quizid;

  @ManyToOne
  @JoinColumn(name = "set_id")
  private SetQuestion setQuestion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member")
  private Member member;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setAnswer(Boolean answer) {
    isAnswer = answer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Member getMember() {
    return member;
  }

  public int getQuizid() {
    return quizid;
  }

  public void setQuizid(int quizid) {
    this.quizid = quizid;
  }

  public void setMember(Member member) {
    this.member = member;
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

  public SetQuestion getSetQuestion() {
    return setQuestion;
  }

  public void setSetQuestion(SetQuestion setQuestion) {
    this.setQuestion = setQuestion;
  }

  public void setSetId(Long setId) {

    SetQuestion setQuestion = new SetQuestion();
    setQuestion.setId(setId);
    this.setSetQuestion(setQuestion);
  }

  public Long getSetId() {
    return setQuestion.getId();
  }
}

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
  private Long score;

  @Column(name = "is_checked")
  private Boolean check;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member")
  private Member member;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Boolean getCheck() {
    return check;
  }

  public void setCheck(Boolean check) {
    this.check = check;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }
}

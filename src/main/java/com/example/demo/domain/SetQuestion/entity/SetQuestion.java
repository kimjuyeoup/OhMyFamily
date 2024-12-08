package com.example.demo.domain.SetQuestion.entity;

import jakarta.persistence.*;

@Entity
public class SetQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Set_id")
  private Long id;

  @Column(name = "Title")
  private String title;

  @Column(name = "Content")
  private String content;

  @Column(name = "Icon")
  private String icon;

  @Column(name = "Score")
  private Long score;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getTitle(String name) {
    return title.replace("{name}", name);
  }

  public String getContent(String name) {
    return content.replace("{name}", name);
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }
}

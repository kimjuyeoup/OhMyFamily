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

  @Column(name = "Type")
  private String type;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getContentsFormatted(String name, String id) {

    if ("select".equalsIgnoreCase(this.type)) {
      String formattedContent = "";
      if ("{content}".equals(this.content)) {
        if (id.equals("1")) {
          formattedContent = "송혜교 닮았다고 하세요.";
        } else if (id.equals("2")) {
          formattedContent = "원빈 닮았다고 하세요.";
        } else if (id.equals("3")) {
          formattedContent = "김태희, 송혜교, 손예진, 고수, 원빈, 정우성 ...더보기.";
        }
      } else {
        formattedContent = content != null ? content.replace("{name}", name) : null;
      }

      String[] contentsArray =
          formattedContent != null ? formattedContent.split(",") : new String[] {};

      return contentsArray;
    } else {
      if ("{content}".equals(content)) {
        if (id.equals("1")) {
          return "송혜교 닮았다고 하세요.";
        } else if (id.equals("2")) {
          return "원빈 닮았다고 하세요.";
        } else if (id.equals("3")) {
          return "김태희, 송혜교, 손예진, 고수, 원빈, 정우성 ...더보기.";
        }
      }
      return content != null ? content.replace("{name}", name) : name;
    }
  }
}

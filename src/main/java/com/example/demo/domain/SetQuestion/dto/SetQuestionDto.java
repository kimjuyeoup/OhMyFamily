package com.example.demo.domain.SetQuestion.dto;

public class SetQuestionDto {

  private Long id;
  private String title;
  private Object content;
  private String icon;
  private String type;

  public SetQuestionDto(Long id, String title, Object content, String icon, String type) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.icon = icon;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}

package com.example.demo.domain.Question.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SubmitDto {
  private Long id;
  private String name;
  private List<String> answer;
  private MultipartFile image;

  public SubmitDto(String name, List<String> answer, MultipartFile image) {
    this.name = name;
    this.answer = answer;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getAnswer() {
    return answer;
  }

  public void setAnswer(List<String> answer) {
    this.answer = answer;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }
}

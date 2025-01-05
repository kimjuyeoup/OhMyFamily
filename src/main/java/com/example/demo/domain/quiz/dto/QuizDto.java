package com.example.demo.domain.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Lombok을 사용하는 경우, getter/setter 및 생성자 자동 생성
@AllArgsConstructor
public class QuizDto {
  Long id;
  Boolean ischeck;
  String name;
  Long score;
  String icon;
}

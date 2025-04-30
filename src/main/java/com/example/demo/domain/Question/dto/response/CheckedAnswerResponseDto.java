package com.example.demo.domain.Question.dto.response;

public record CheckedAnswerResponseDto(
    String content,
    String icon,
    String title,
    String type,
    String answer,
    String name,
    Boolean isAnswer) {}

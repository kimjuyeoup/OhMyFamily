package com.example.demo.domain.Question.dto.response;

import lombok.Builder;

@Builder
public record AnswerResponse(Long id, String title, String answer, String icon) {}

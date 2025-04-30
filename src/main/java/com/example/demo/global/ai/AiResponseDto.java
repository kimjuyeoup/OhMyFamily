package com.example.demo.global.ai;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class AiResponseDto {

  @JsonProperty("choices")
  private List<Choice> choices;

  @Getter
  public static class Choice {
    @JsonProperty("message")
    private Message message;

    @JsonProperty("index")
    private Integer index;
  }

  @Getter
  public static class Message {
    String role;
    String content;
  }

  public String getAnswer() {
    return choices.get(0).message.content;
  }
}

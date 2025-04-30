package com.example.demo.global.ai;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class AiRequestDto {

  private String model = "gpt-4o-mini";

  private List<Message> messages;

  public AiRequestDto(List<Message> messages) {
    this.messages = messages;
  }

  @Getter
  @AllArgsConstructor
  public static class Message {
    private Role role;
    private List<Content> content;
  }

  @RequiredArgsConstructor
  @Getter
  public enum Role {
    USER("user"),
    SYSTEM("system");
    @JsonValue private final String value;
  }

  @AllArgsConstructor
  @Getter
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Content {
    private Type type;

    private String text;

    public static Content textContent(String text) {
      return new Content(Type.TEXT, text);
    }
  }

  @RequiredArgsConstructor
  @Getter
  public enum Type {
    TEXT("text");
    @JsonValue private final String value;
  }

  public static AiRequestDto from(AiRequestBodyDto requestBodyDto) {
    return new AiRequestDto(createMessage(requestBodyDto));
  }

  public static List<Message> createMessage(AiRequestBodyDto requestBodyDto) {
    return List.of(
        new Message(
            Role.SYSTEM,
            List.of(
                Content.textContent(AiPrompt.getStartPrompt(requestBodyDto)),
                Content.textContent(AiPrompt.getMainPrompt(requestBodyDto)))));
  }
}

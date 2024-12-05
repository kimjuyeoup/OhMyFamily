package com.example.demo.domain.Question.dto;

import lombok.Getter;

@Getter
public enum QuestionEnum {
  LASTCALL(
      "select",
      "{name}와\n 언제 마지막으로 통화했나요?",
      false,
      "",
      "오늘, 지난 주, 지난 달, 전화 자주 할게요 죄송해요 사랑해요♥",
      "call",
      10L,
      1L),
  BIRTHDAY(
      "number", "{name}는\n 언제 태어나셨나요?", false, "", "작성 예시) 1998/12/22 (숫자 8자리)", "angel", 20L, 2L),
  BIRTH(
      "number",
      "{name}는\n 올해 몇 번째 생일을 맞이하셨나요?",
      false,
      "",
      "이거 모르면 진짜 반성해야 댑니다.",
      "birth",
      10L,
      3L),
  FOOD("input", "{name}가\n 가장 좋아하는 음식은 무엇인가요?", false, "", "참고로 저는 소고기 좋아합니다.", "food", 20L, 4L),
  MUSIC("input", "{name}가\n 최근에 빠진 음악은 무엇인가요?", false, "", "어머니의 급상승차트♬", "music", 10L, 5L),
  HOBBY("input", "{name}가\n 요즘 빠져있는 취미는 무엇인가요?", false, "", "", "hobby", 20L, 6L),
  ACTOR("input", "{name}와\n 닮은 연예인은 누구인가요?", false, "", "송혜교 닮았다고 하세요.", "actor", 10L, 7L),
  MEMORY("input", "{name}가 제일 좋아하는\n 나와의 기억은 무엇인가요?", false, "", "", "memory", 20L, 8L),
  WISH(
      "input",
      "{name}가\n 가장 이루고 싶은 소원은 무엇일까요?",
      false,
      "",
      "로또 당첨... 로또 당첨... 로또 당첨... 더보기",
      "wish",
      10L,
      9L),
  CURRENT_THOUGHT("input", "{name}가\n 생각하는 현재 나의 모습은 어떨까요?", false, "", "", "think", 20L, 10L),
  UPLOAD(
      "upload",
      "간직하고 있는 {name}의\n 사진이 있다면 보여주세요!",
      false,
      "",
      "업로드한 사진은 {name}가 보시게 될\n 채점 페이지에 반영됩니다.",
      "",
      20L,
      11L);

  private String type;
  private String title;
  private Boolean isAnswer;
  private String answer;
  private String content;
  private String icon;
  private Long score;
  private Long number;

  QuestionEnum(
      String type,
      String title,
      Boolean isAnswer,
      String answer,
      String content,
      String icon,
      Long score,
      Long number) {
    this.type = type;
    this.title = title;
    this.isAnswer = isAnswer;
    this.answer = answer;
    this.content = content;
    this.icon = icon;
    this.score = score;
    this.number = number;
  }

  public String getTitle(String name) {
    return title.replace("{name}", name);
  }

  public String getContent(String name) {
    return content.replace("{name}", name);
  }
}

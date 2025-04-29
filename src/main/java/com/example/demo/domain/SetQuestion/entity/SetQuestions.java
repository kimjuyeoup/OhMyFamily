package com.example.demo.domain.SetQuestion.entity;

public enum SetQuestions {
  CONTENT1(
      "{name}{c} 언제 마지막으로\n 통화했나요?", "오늘, 지난 주, 지난 달, 전화 자주 할게요 죄송해요 사랑해요♥", "call", "select", 10L),
  CONTENT2("{name}{a}\n 언제 태어나셨나요?", "작성 예시) 1998/12/22 (숫자 8자리)", "angel", "date", 10L),
  CONTENT3("{name}{a} 올해 몇 번째\n 생일을 맞이하셨나요?", "이거 모르면 진짜 반성해야 댑니다.", "birth", "number", 10L),
  CONTENT4("{name}{b} 가장 좋아하는\n 음식은 무엇인가요?", "참고로 저는 소고기 좋아합니다.", "food", "input", 10L),
  CONTENT5("{name}{b} 최근에 빠진\n 음악은 무엇인가요?", "{name}의 급상승차트♬", "music", "input", 10L),
  CONTENT6("{name}{b} 요즘 빠져있는\n 취미는 무엇인가요?", "", "hobby", "input", 10L),
  CONTENT7("{name}{c} 닮은 연예인은\n 누구인가요?", "{content}", "actor", "input", 10L),
  CONTENT8("{name}{b} 제일 좋아하는\n 나와의 기억은 무엇인가요?", "", "memory", "input", 10L),
  CONTENT9(
      "{name}{b} 가장 이루고 싶은\n 소원은 무엇일까요?", "로또 당첨... 로또 당첨... 로또 당첨... 더보기.", "wish", "input", 10L),
  CONTENT10("{name}{b} 생각하는\n 현재 나의 모습은 어떨까요?", "", "think", "input", 10L),
  CONTENT11(
      "간직하고 있는 {name}의\n 사진이 있다면 보여주세요!",
      "업로드한 사진은 {name}{b} 보시게 될\n 채점 페이지에 반영됩니다.",
      "",
      "upload",
      10L);

  private final String content;
  private final String title;
  private final String icon;
  private final String type;
  private final Long score;

  SetQuestions(String title, String content, String icon, String type, Long score) {
    this.content = content;
    this.title = title;
    this.icon = icon;
    this.type = type;
    this.score = score;
  }

  public String getContent() {
    return content;
  }

  public String getTitle() {
    return title;
  }

  public String getIcon() {
    return icon;
  }

  public String getType() {
    return type;
  }

  public Long getScore() {
    return score;
  }
}

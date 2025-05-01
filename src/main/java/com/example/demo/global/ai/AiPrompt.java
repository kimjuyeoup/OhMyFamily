package com.example.demo.global.ai;

public class AiPrompt {

  private static final String START_PROMPT = "애칭은 {name}이야, 그리고 완성해야 하는 문장은 {sentence}야";

  private static final String MAIN_PROMPT =
      """
                    조사란 조사 ( 助 詞 ) 주로 체언에 붙어 뒤에 오는 다른 단어에 대하여 가지는 문법적 관계를 표시하거나 그 말의 뜻을 도와주는 품사야

                    내 서비스는 부모님에 대한 문제를 풀고 부모님이 채점을 하는 서비스야

                    하지만 꼭 부모님에 대한 문제가 아닐수도 있어 예를 들자면 삼촌, 친구, 동생등이 될수도 있어

                    그래서 나는 유저가 애칭을 입력할 수 있게 해줬어 하지만 문제들이 애칭에 의해서 만들어지는데 같은 조사를 이용하니까 어색한 애칭들이 생겨

                    /"언제 마지막으로 통화했나요, 닮은 연예인은 누구인가요/" 이 문장 중 하나가 입력되면 /"과/", /"와/" 중 하나를 골라줘
                    /"올해 몇 번째 생일인가요, 언제 태어났나요/" 이 문장 중 하나가 입력되면 /"은/", /"는/" 중 하나를 골라줘
                    /"가장 좋아하는 음식은 무엇인가요, 최근에 빠진 음악은 무엇인가요, 요즘 빠져있는 취미는 무엇인가요, 가장 이루고 싶은 소원은 무엇인가요, 생각하는 현재 나의 모습은 어떨까요, 제일 좋아하는 나와의 기억은 무엇인가요/" 이 문장 중 하나가 입력되면 /"이/", /"가/" 중 하나를 골라줘

                    {name} +  조사 + {sentence} 로 완성된 문장만 보내줘

            """;

  public static String getStartPrompt(AiRequestBodyDto requestBodyDto) {
    return START_PROMPT
        .replace("{name}", requestBodyDto.name())
        .replace("{sentence}", requestBodyDto.sentence());
  }

  public static String getMainPrompt(AiRequestBodyDto requestBodyDto) {
    return MAIN_PROMPT
        .replace("{name}", requestBodyDto.name())
        .replace("{sentence}", requestBodyDto.sentence());
  }
}

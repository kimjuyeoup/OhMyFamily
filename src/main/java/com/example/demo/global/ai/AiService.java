package com.example.demo.global.ai;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

  private final OpenAiClient openAiClient;

  public String getNameFromAi(AiRequestBodyDto requestBodyDto) {
    AiRequestDto requestDto = AiRequestDto.from(requestBodyDto);
    AiResponseDto responseDto = openAiClient.nameResponse(requestDto);
    return responseDto.getAnswer();
  }
}

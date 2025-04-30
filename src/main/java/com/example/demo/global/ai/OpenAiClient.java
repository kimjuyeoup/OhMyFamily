package com.example.demo.global.ai;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.global.config.OpenAiClientConfig;

@FeignClient(
    name = "open-ai-client",
    url = "https://api.openai.com/v1/chat/completions",
    configuration = OpenAiClientConfig.class)
public interface OpenAiClient {

  @PostMapping
  AiResponseDto nameResponse(@RequestBody AiRequestDto request);
}

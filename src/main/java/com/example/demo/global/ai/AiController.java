package com.example.demo.global.ai;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.global.exception.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

  private final AiService aiService;

  @PostMapping("/name")
  public BaseResponse<String> getNameFromAi(@RequestBody AiRequestBodyDto requestBodyDto) {
    return BaseResponse.onSuccess(aiService.getNameFromAi(requestBodyDto));
  }
}

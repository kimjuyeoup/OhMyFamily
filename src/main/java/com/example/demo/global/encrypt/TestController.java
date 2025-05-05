package com.example.demo.global.encrypt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

  private final EncryptService encryptService;

  @PostMapping
  public Long de(@RequestParam String de) throws Exception {
    return encryptService.decrypt(de);
  }
}

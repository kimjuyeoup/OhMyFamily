package com.example.demo.domain.Qname.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Qname.dto.ChangeRequest;
import com.example.demo.domain.Qname.service.QnameService;
import com.example.demo.global.exception.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class qnameController {
  private final QnameService qnameService;

  @PostMapping("/change")
  public BaseResponse<String> getChangeByName(@RequestBody ChangeRequest request) {
    return BaseResponse.onSuccess(
        qnameService.getChangeByName(request.getName(), request.getQuizid()));
  }
}

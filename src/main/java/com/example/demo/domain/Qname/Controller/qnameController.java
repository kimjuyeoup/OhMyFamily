package com.example.demo.domain.Qname.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Qname.dto.ChangeRequest;
import com.example.demo.domain.Qname.service.QnameService;
import com.example.demo.global.encrypt.EncryptService;
import com.example.demo.global.exception.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class qnameController {
  private final QnameService qnameService;
  private final EncryptService encryptService;

  @PostMapping("/change")
  public BaseResponse<String> getChangeByName(@RequestBody ChangeRequest request) throws Exception {
    return BaseResponse.onSuccess(
        qnameService.getChangeByName(
            request.getName(), encryptService.decrypt(request.getQuizid()).intValue()));
  }
}

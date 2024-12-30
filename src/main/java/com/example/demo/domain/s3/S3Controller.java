package com.example.demo.domain.s3;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.global.exception.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class S3Controller {

  private final S3UploadService s3UploadService;

  @PatchMapping(
      value = "/upload",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public BaseResponse<String> uploadImage(
      @RequestPart(value = "image", required = false) MultipartFile file) {
    if (file != null && !file.isEmpty()) {
      String imageUrl = s3UploadService.uploadFile(file);
      return BaseResponse.onSuccess(imageUrl);
    }
    return BaseResponse.onSuccess("No file uploaded.");
  }
}
ggg
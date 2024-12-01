package com.example.demo.domain.s3;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.global.config.AmazonConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploadService {

  private final AmazonS3 amazonS3;
  private final AmazonConfig amazonConfig;

  public String uploadFile(MultipartFile file) {
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize());
    try {
      amazonS3.putObject(
          new PutObjectRequest(
              amazonConfig.getBucket(), generateQuizKeyName(), file.getInputStream(), metadata));
    } catch (IOException e) {
      log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
    }

    return amazonS3.getUrl(amazonConfig.getBucket(), generateQuizKeyName()).toString();
  }

  public String generateQuizKeyName() {
    return "quiz" + '/' + UUID.randomUUID().toString();
  }
}

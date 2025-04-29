package com.example.demo.domain.Qname.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Qname.entity.QnameEntity;
import com.example.demo.domain.Qname.repository.QnameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QnameService {

  private final QnameRepository qnameRepository;

  public String getChangeByName(String name, int quizid) {
    QnameEntity entity = qnameRepository.findFirstByQuizid(quizid);

    if (entity != null) {
      entity.setName(name);
    } else {
      entity = QnameEntity.builder().name(name).quizid(quizid).build();
      qnameRepository.save(entity);
    }

    return "저장되었습니다";
  }
}

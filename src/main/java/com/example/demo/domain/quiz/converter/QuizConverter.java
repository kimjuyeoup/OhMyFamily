package com.example.demo.domain.quiz.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.quiz.dto.request.SetQuizNicknameRequest;
import com.example.demo.domain.quiz.dto.response.SetQuizNicknameResponse;
import com.example.demo.domain.quiz.entity.Quiz;

public class QuizConverter {

  public static Quiz toQuiz(SetQuizNicknameRequest request, Member member) {
    return Quiz.builder().nickname(request.getNickname()).member(member).build();
  }

  public static SetQuizNicknameResponse toSetQuizNicknameResponse(Quiz quiz) {
    return SetQuizNicknameResponse.builder().nickname(quiz.getNickname()).build();
  }
}

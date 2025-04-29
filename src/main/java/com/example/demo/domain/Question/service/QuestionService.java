package com.example.demo.domain.Question.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Qname.repository.QnameRepository;
import com.example.demo.domain.Question.dto.InfoDto;
import com.example.demo.domain.Question.dto.response.AnswerResponse;
import com.example.demo.domain.Question.entity.QuestionEntity;
import com.example.demo.domain.Question.repository.QuestionRepository;
import com.example.demo.domain.SetQuestion.dto.SetQuestionDto;
import com.example.demo.domain.SetQuestion.entity.SetQuestion;
import com.example.demo.domain.SetQuestion.repository.SetQuestionRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.quiz.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

  private final SetQuestionRepository setQuestionRepository;
  private final QuestionRepository questionRepository;
  private final MemberRepository memberRepository;
  private final QuizRepository quizRepository;
  private final QnameRepository qnameRepository;

  public List<SetQuestionDto> getQuestionByName(String name, String id) {

    List<SetQuestion> questions = setQuestionRepository.findAll();

    return questions.stream()
        .map(
            question ->
                new SetQuestionDto(
                    question.getId(),
                    question.getTitle(name),
                    question.getContentsFormatted(name, id),
                    question.getIcon(),
                    question.getType()))
        .collect(Collectors.toList());
  }

  public Map<String, Object> getAnswerByName(int quizid) {
    if (quizRepository.findNameByQuizid(quizid) == null) {
      List<QuestionEntity> questions = questionRepository.findAnswerByQuizid(quizid);

      List<AnswerResponse> answers =
          questions.stream()
              .filter(question -> question.getSetId() <= 10)
              .map(
                  question -> {
                    SetQuestion setQuestion = question.getSetQuestion();
                    String title =
                        setQuestion != null ? setQuestion.getTitle(question.getName()) : null;
                    String icon = setQuestion != null ? setQuestion.getIcon() : null;

                    return AnswerResponse.builder()
                        .id(question.getSetId())
                        .answer(question.getAnswer())
                        .title(title)
                        .icon(icon)
                        .build();
                  })
              .collect(Collectors.toList());

      Map<String, Object> result = new HashMap<>();
      result.put("data", answers);
      result.put("image", questionRepository.findAnswerByQuizid11(quizid));

      return result;
    } else {
      throw new IllegalArgumentException("해당 퀴즈 ID(" + quizid + ")에 대한 점수가 이미 존재합니다.");
    }
  }

  public InfoDto getInfo(int quizid) {
    String name = questionRepository.findNameByQuizid(quizid).orElse(null);
    Long member = questionRepository.findMemberByQuizid(quizid).orElse(null);
    if (qnameRepository.findNameByQuizid(quizid) == null) {
      String kakao_nickname = memberRepository.findKakaoNicknameByMember(member);
      return new InfoDto(kakao_nickname, name);
    } else {
      String kakao_nickname = qnameRepository.findNameByQuizid(quizid);
      return new InfoDto(kakao_nickname, name);
    }
  }
}

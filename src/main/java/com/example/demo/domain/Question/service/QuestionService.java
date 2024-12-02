package com.example.demo.domain.Question.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Question.dto.*;
import com.example.demo.domain.Question.entity.QuestionEntity;
import com.example.demo.domain.Question.repository.QuestionRepository;

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;

  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public List<QuestionDto> getQuestionsByName(String name) {

    List<QuestionEntity> existingQuestions = questionRepository.findByName(name);
    if (!existingQuestions.isEmpty()) {
      questionRepository.deleteAll(existingQuestions); // 기존 데이터 삭제
    }
    questionRepository.save(
        new QuestionEntity(
            "select",
            name + "와\n 언제 마지막으로 통화했나요?",
            false,
            "",
            "오늘, 지난 주, 지난 달, 전화 자주 할게요 죄송해요 사랑해요♥",
            "call",
            10L,
            name,
            1L));
    questionRepository.save(
        new QuestionEntity(
            "number",
            name + "는\n 언제 태어나셨나요?",
            false,
            "",
            "작성 예시) 1998/12/22 (숫자 8자리)",
            "angel",
            20L,
            name,
            2L));
    questionRepository.save(
        new QuestionEntity(
            "number",
            name + "는\n 올해 몇 번째 생일을 맞이하셨나요?",
            false,
            "",
            "이거 모르면 진짜 반성해야 댑니다.",
            "birth",
            10L,
            name,
            3L));
    questionRepository.save(
        new QuestionEntity(
            "input",
            name + "가\n 가장 좋아하는 음식은 무엇인가요?",
            false,
            "",
            "참고로 저는 소고기 좋아합니다.",
            "food",
            20L,
            name,
            4L));
    questionRepository.save(
        new QuestionEntity(
            "input",
            name + "가\n 최근에 빠진 음악은 무엇인가요?",
            false,
            "",
            "어머니의 급상승차트♬",
            "music",
            10L,
            name,
            5L));
    questionRepository.save(
        new QuestionEntity(
            "input", name + "가\n 요즘 빠져있는 취미는 무엇인가요?", false, "", "", "hobby", 20L, name, 6L));
    questionRepository.save(
        new QuestionEntity(
            "input",
            name + "와\n 닮은 연예인은 누구인가요?",
            false,
            "",
            "송혜교 닮았다고 하세요.",
            "actor",
            10L,
            name,
            7L));
    questionRepository.save(
        new QuestionEntity(
            "input", name + "가 제일 좋아하는\n 나와의 기억은 무엇인가요?", false, "", "", "memory", 20L, name, 8L));
    questionRepository.save(
        new QuestionEntity(
            "input",
            name + "가 가장 이루고 싶은 소원은 무엇일까요?",
            false,
            "",
            "로또 당첨... 로또 당첨... 로또 당첨... 더보기",
            "wish",
            10L,
            name,
            9L));
    questionRepository.save(
        new QuestionEntity(
            "input", name + "가 생각하는 현재 나의 모습은 어떨까요?", false, "", "", "think", 20L, name, 10L));
    questionRepository.save(
        new QuestionEntity(
            "upload",
            "간직하고 있는 " + name + "의\n 사진이 있다면 보여주세요!",
            false,
            "",
            "업로드한 사진은 " + name + "가 보시게 될 채점 페이지에 반영됩니다.",
            "",
            20L,
            name,
            11L));

    List<QuestionEntity> questions = questionRepository.findByName(name);
    return questions.stream()
        .map(
            question ->
                new QuestionDto(
                    question.getNumber(),
                    question.getType(),
                    question.getTitle(),
                    question.getContent(),
                    question.getIcon()))
        .collect(Collectors.toList());
  }

  public Map<String, Object> getAnswerByName(String name) {
    List<QuestionEntity> questions = questionRepository.findAnswerByName(name);
    List<AnswerDto> answers =
        questions.stream()
            .limit(10)
            .map(
                question ->
                    new AnswerDto(
                        question.getNumber(),
                        question.getTitle(),
                        question.getAnswer(),
                        question.getIcon()))
            .collect(Collectors.toList());
    String imageUrl = questions.isEmpty() ? null : questions.get(questions.size() - 1).getIcon();

    Map<String, Object> result = new HashMap<>();
    result.put("data", answers);
    if (imageUrl != null && !imageUrl.isEmpty()) {
      result.put("imageUrl", imageUrl);
    }
    return result;
  }

  public ScoreDto updateScoreByNickname(ScoreDto scoreDto) {

    List<QuestionEntity> questions = questionRepository.findByName(scoreDto.getNickname());
    if (questions.isEmpty()) {
      throw new IllegalArgumentException("Questions not found for user: " + scoreDto.getNickname());
    }

    for (ResultDto updatedResult : scoreDto.getResult()) {
      Optional<QuestionEntity> optionalQuestion =
          questionRepository.findById(updatedResult.getId());

      if (optionalQuestion.isPresent()) {
        QuestionEntity question = optionalQuestion.get();
        question.setIsAnswer(updatedResult.getIsCorrect());
        questionRepository.save(question);
      }
    }
    Long score = questionRepository.findAllScore();
    String icon = "P" + score;

    return new ScoreDto(score, icon, scoreDto.getNickname());
  }

  public SubmitDto updateSubmitByNickname(SubmitDto submitDto) {
    List<QuestionEntity> questions = questionRepository.findByName(submitDto.getName());
    List<String> answers = submitDto.getAnswer();
    for (int i = 0; i < questions.size(); i++) {
      QuestionEntity question = questions.get(i);
      if (i < answers.size()) {
        question.setAnswer(answers.get(i));
      }
    }
    questionRepository.saveAll(questions);
    return submitDto;
  }
}

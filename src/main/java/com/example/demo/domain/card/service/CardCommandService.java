package com.example.demo.domain.card.service;

import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.card.converter.CardConverter;
import com.example.demo.domain.card.dto.request.CardRequest;
import com.example.demo.domain.card.entity.Card;
import com.example.demo.domain.card.repository.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CardCommandService {

  private final CardRepository cardRepository;

  @PostConstruct
  public void init() {
    Card card = new Card();
    card.setContent("사실 당신은 인간이 된 꿈을 꾸고 있는\n 감자인 것입니다.\n 감자가 사람이 되기 위해서는 쑥과 마늘을...더보기");
    card.setId(1L);
    card.setIcon("https://oh-my-family-buckets.s3.ap-northeast-2.amazonaws.com/20-2.png");
    card.setSubtitle("저는 말하는 감자라구요. 아시겠어요?");
    card.setScore(20L);
    card.setTitle("말하는 감자");
    Card card2 = new Card();
    card2.setContent(
        "부모님을 사랑하지만 마음처럼 안 따라주는 당신!\n 오늘 같은 날은 서른한 가지 맛 아이스크림 먹으면서\n 다정한 시간 보내보는 건 어때요?");
    card2.setId(2L);
    card2.setIcon("https://oh-my-family-buckets.s3.ap-northeast-2.amazonaws.com/40-1.png");
    card2.setSubtitle("효도를 이렇게 콕 찍어서 뇸!");
    card2.setScore(40L);
    card2.setTitle("효자 맛보기 스푼");
    Card card3 = new Card();
    card3.setContent(
        "부모님을 사랑하지만 표현에 조금 서툰 당신!\n 어떤 대답이 맞고 틀렸는지를 주제로 얘기하면서\n 부모님과 조금 더 가까워지는 건 어때요?");
    card3.setId(3L);
    card3.setIcon("https://oh-my-family-buckets.s3.ap-northeast-2.amazonaws.com/60-1.png");
    card3.setSubtitle("어어색할거같진않고 분명히어색할거같아요");
    card3.setScore(60L);
    card3.setTitle("효자 호소인");
    Card card4 = new Card();
    card4.setContent(
        "효자에 가까워지고 있는 당신!\n 이정도면 스스로를 대견하게 생각해도 될 듯.\n효자에 가까워지고 있는 당신!\n 이정도면 스스로를 대견하게 생각해도 될 듯.");
    card4.setId(4L);
    card4.setIcon("https://oh-my-family-buckets.s3.ap-northeast-2.amazonaws.com/80-1.png");
    card4.setSubtitle("홍진효");
    card4.setScore(80L);
    card4.setTitle("홍진효");
    Card card5 = new Card();
    card5.setContent("1등 효도템 부럽지 않은 성골 효자인 당신!\n 지금처럼 쭉 다정한 효자가 되어주세요!");
    card5.setId(5L);
    card5.setIcon("https://oh-my-family-buckets.s3.ap-northeast-2.amazonaws.com/90-%60.png");
    card5.setSubtitle("사랑하니까 바디프렌드 (둘 다 광고 아님)");
    card5.setScore(90L);
    card5.setTitle("인간 코지마");
    Card card6 = new Card();
    card6.setContent(
        "지금부터 부모님에 대한 지지관계를 철회한다.\n 오늘부터 부모님과 한 몸으로 간주하여\n 부모님에 대한 공격은 나에 대한 공격으로 간주한다.");
    card6.setId(6L);
    card6.setIcon("https://oh-my-family-buckets.s3.ap-northeast-2.amazonaws.com/100-1.png");
    card6.setSubtitle("이거 본인이 맞추고 채점하는 거 아니그든요.");
    card6.setScore(100L);
    card6.setTitle("혹시 본인이세요?");

    List<Card> entities = Arrays.asList(card, card2, card3, card4, card5, card6);

    cardRepository.saveAll(entities);
  }

  public List<Card> findCardsByScore(Long score) {
    Long scorenum = 0L;
    if (score >= 0 && score <= 20) {
      scorenum = 20L;
    } else if (score > 20 && score <= 40) {
      scorenum = 40L;
    } else if (score > 40 && score <= 60) {
      scorenum = 60L;
    } else if (score > 60 && score <= 80) {
      scorenum = 80L;
    } else if (score > 80 && score <= 90) {
      scorenum = 90L;
    } else if (score > 90 && score <= 100) {
      scorenum = 100L;
    }
    return cardRepository.findByScore(scorenum);
  }

  /*public void createCard(CardRequest request) {
    cardRepository.save(CardConverter.toCard(request));
  }*/

  public void updateCard(CardRequest request) {
    cardRepository.save(CardConverter.toCard(request));
  }

  public void deleteCard(Long cardId) {
    cardRepository.deleteById(cardId);
  }
}

package com.example.demo.domain.member.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(name = "kakao_id")
  private Long kakaoId;

  @Column(name = "kakao_nickname")
  private String kakaoNickname;

  @Setter
  @Column(name = "refresh_token")
  private String refreshToken;

  public void ChangeKakaoNickname(String nickname) {
    this.kakaoNickname = nickname;
  }
}

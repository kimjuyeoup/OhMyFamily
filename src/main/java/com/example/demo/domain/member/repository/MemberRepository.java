package com.example.demo.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByKakaoId(Long kakaoId);

  Optional<Member> findById(Long id);

  @Query("SELECT q.kakaoNickname FROM Member q WHERE q.id = :memberId")
  String findKakaoNicknameByMember(@Param("memberId") Long memberId);
}

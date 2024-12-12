package com.example.demo.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByKakaoNickname(String kakaoNickname);

  Optional<Member> findById(Long id);
}

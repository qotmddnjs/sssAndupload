package com.koreait.exam.sssandupload.app.member.repository;

import com.koreait.exam.sssandupload.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByUsername(String username);
}
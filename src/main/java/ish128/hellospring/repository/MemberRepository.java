package ish128.hellospring.repository;

import ish128.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

  Member save(Member member);
  Optional<Member> findById(Long id);
  Optional<Member> findAnyByName(String name);

  List<Member> findByName(String name);
  List<Member> findAll();


}

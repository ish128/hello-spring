package ish128.hellospring.repository;

import ish128.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

  @Override
  Optional<Member> findAnyByName(String name);

  @Override
  List<Member> findByName(String name);
}

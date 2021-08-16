package ish128.hellospring.service;

import ish128.hellospring.domain.Member;
import ish128.hellospring.repository.MemberRepository;
import ish128.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

  @Autowired MemberRepository memberRepository;
  @Autowired MemberService memberService;

  @Test
  void join() {
    //given
    Member member = new Member();
    member.setName("spring");
    //when
    Long memberId = memberService.join(member);
    //then
    assertThat(memberRepository.findAnyByName(member.getName()).get().getId()).isEqualTo(memberId);
  }

  @Test
  void join_validateDuplicateMemeber() {
    //given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    //when
    Long member1Id = memberService.join(member1);

    //then
    IllegalStateException result = assertThrows(IllegalStateException.class, ()->memberService.join(member2));
    assertThat(result.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    /*try{
      memberService.join(member2);
      fail();
    }catch (IllegalStateException e){
      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }*/
  }

  @Test
  void findMembers() {
    //given
    Member member1 = new Member();
    member1.setName("spring1");

    Member member2 = new Member();
    member2.setName("spring2");

    memberRepository.save(member1);
    memberRepository.save(member2);

    //when
    List<Long> results = memberService.findMembers().stream().map(Member::getId).collect(Collectors.toList());

    //then
    assertThat(results).contains(member1.getId(), member2.getId());
  }

  @Test
  void findOne() {
    //given
    Member member1 = new Member();
    member1.setName("spring1");

    Member member2 = new Member();
    member2.setName("spring2");

    memberRepository.save(member1);
    memberRepository.save(member2);

    //when
    Member result = memberService.findOne(member1.getId()).get();

    //then
    assertThat(result.getId()).isEqualTo(member1.getId());
  }
}

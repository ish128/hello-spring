package ish128.hellospring.service;

import ish128.hellospring.domain.Member;
import ish128.hellospring.repository.MemoryMemberRepository;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

  MemoryMemberRepository memberRepository;
  MemberService memberService;

  @BeforeEach
  void beforeEach(){
     memberRepository = new MemoryMemberRepository();
     memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach(){
    memberRepository.clearStore();
  }

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
    List<Member> results = memberService.findMembers();

    //then
    assertThat(results).contains(member1, member2);
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
    assertThat(result).isEqualTo(member1);
  }
}
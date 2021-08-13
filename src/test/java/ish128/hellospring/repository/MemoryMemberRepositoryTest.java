package ish128.hellospring.repository;

import ish128.hellospring.domain.Member;

import org.assertj.core.api.Assertions;
import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;


class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach(){
    repository.clearStore();
  }

  @Test
  public void save(){
    Member member = new Member();
    member.setName("spring");
    repository.save(member);

    Member result = repository.findById(member.getId()).get();
    assertThat(result).isEqualTo(member);
  }

  @Test
  public void findAnyByName(){
    Member member1 = new Member();
    member1.setName("spring1");

    Member member2 = new Member();
    member2.setName("spring2");

    repository.save(member1);
    repository.save(member2);

    Member result = repository.findAnyByName(member1.getName()).get();
    assertThat(result).isEqualTo(member1);
  }

  @Test
  public void findByName(){
    String sameName = "spring";
    Member member1 = new Member();
    member1.setName(sameName);

    Member member2 = new Member();
    member2.setName(sameName);

    repository.save(member1);
    repository.save(member2);

    List<Member> results = repository.findByName(sameName);
    assertThat(results.size()).isEqualTo(2);
    MatcherAssert.assertThat(results.stream().map(Member::getName).collect(Collectors.toList())
                            , everyItem(equalTo(sameName)));
  }

  @Test
  public void findAll(){
    Member member1 = new Member();
    member1.setName("spring1");

    Member member2 = new Member();
    member2.setName("spring2");

    repository.save(member1);
    repository.save(member2);

    List<Member> results = repository.findAll();

    assertThat(results.size()).isEqualTo(2);
    assertThat(results).contains(member1, member2);
  }
}
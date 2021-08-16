package ish128.hellospring;

import ish128.hellospring.aop.TimeTraceAop;
import ish128.hellospring.repository.JdbcMemberRepository;
import ish128.hellospring.repository.JdbcTemplateMemberRepository;
import ish128.hellospring.repository.JpaMemberRepository;
import ish128.hellospring.repository.MemberRepository;
import ish128.hellospring.repository.MemoryMemberRepository;
import ish128.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  @Autowired
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository);
  }

/*  @Bean
  public TimeTraceAop timeTraceAop(){    return new TimeTraceAop();  }*/
}

package ish128.hellospring;

import ish128.hellospring.repository.JdbcMemberRepository;
import ish128.hellospring.repository.JdbcTemplateMemberRepository;
import ish128.hellospring.repository.MemberRepository;
import ish128.hellospring.repository.MemoryMemberRepository;
import ish128.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

  public SpringConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private final DataSource dataSource;

  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository(){

    //return new MemoryMemberRepository();

    // return new JdbcMemberRepository(dataSource);

    return new JdbcTemplateMemberRepository(dataSource);
  }
}

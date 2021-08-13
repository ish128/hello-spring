package ish128.hellospring.controller;

import ish128.hellospring.domain.Member;
import ish128.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new")
  public String createForm(){
    return "/members/createMemberForm.html";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form){
    Member member = new Member();
    member.setName(form.getName());
    memberService.join(member);
    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model){
    List<Member> list = memberService.findMembers();
    model.addAttribute("members", list);
    return "/members/memberList.html";
  }
}

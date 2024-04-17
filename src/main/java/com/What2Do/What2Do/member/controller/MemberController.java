package com.What2Do.What2Do.member.controller;

import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.etc.MemberRequestDto;

import com.What2Do.What2Do.member.repository.MemberRepository;
import com.What2Do.What2Do.member.service.MemberService;
import com.What2Do.What2Do.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostService postService;



    // 홈 화면
    @GetMapping("/")
    public String Home(){
        return "home";
    }

    // 회원 가입
    @GetMapping("/member/new")
    public String memberCreateForm(Model model, Member member){
        model.addAttribute("memberRequestDto" , member);
        return "member/member-register";
    }


    @PostMapping("/member/new")
    public String createForm (MemberRequestDto memberRequestDto) throws Exception {
        memberService.create(memberRequestDto);
        return"redirect:/";
    }

    @GetMapping("/members")
    public String members (Model model) throws Exception{
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "member/member-myPage";

    }

    @GetMapping("member/login")
    public String memberLoginForm(){
        return "member/login";
    }



}


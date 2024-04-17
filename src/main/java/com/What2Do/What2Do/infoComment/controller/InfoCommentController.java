package com.What2Do.What2Do.infoComment.controller;

import com.What2Do.What2Do.comment.etc.CommentsDto;
import com.What2Do.What2Do.infoComment.domain.InfoComment;
import com.What2Do.What2Do.infoComment.etc.InfoCommentDto;
import com.What2Do.What2Do.infoComment.service.InfoCommentService;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class InfoCommentController {


    @Autowired
    private InfoCommentService infoCommentService;

    @Autowired
    private MemberRepository memberRepository;


    // 댓글 생성
    @PostMapping("infoComment/new")
    public String create (InfoCommentDto infoCommentDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member currentUser = memberRepository.findByUserName(authentication.getName());

        infoCommentDto.setMemberId(currentUser.getId());
        infoCommentService.create(infoCommentDto);
        Long informationId = infoCommentDto.getInformationId();
        return "redirect:/information/detail/"  + informationId;
    }

    @PostMapping("/infoComment/update/{id}")
    public String infoCommentUpdate(@PathVariable("id") Long id, InfoCommentDto infoCommentDto) throws Exception {
        infoCommentDto.setId(String.valueOf(id)); // 수정할 댓글의 ID 설정
        infoCommentService.update(infoCommentDto);
        Long informationId = infoCommentDto.getInformationId();
        return "redirect:/information/detail/"  + informationId;
    }

    @GetMapping("/infoComment/delete")
    public String infoCommentDelete(@RequestParam(value = "id") Long id) throws Exception {
        Long informationId = infoCommentService.findInformationIdById(id);
        infoCommentService.delete(id);

        return "redirect:/information/detail/"  + informationId;
    }




}

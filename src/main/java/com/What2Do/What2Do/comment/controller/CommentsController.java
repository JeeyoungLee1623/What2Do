package com.What2Do.What2Do.comment.controller;


import com.What2Do.What2Do.comment.domain.Comments;
import com.What2Do.What2Do.comment.etc.CommentsDto;
import com.What2Do.What2Do.comment.service.CommentsService;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.repository.MemberRepository;
import com.What2Do.What2Do.post.domain.Post;
import com.What2Do.What2Do.post.etc.PostDto;
import com.What2Do.What2Do.post.repository.PostRepository;
import com.What2Do.What2Do.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

@Controller
public class CommentsController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("comments/new")
    public String create (CommentsDto commentsDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member currentUser = memberRepository.findByUserName(authentication.getName());

        // 현재 로그인한 멤버의 아이디를 CommentsDto에 저장
        commentsDto.setMemberId(currentUser.getId());
        commentsService.create(commentsDto);
        Long postId = commentsDto.getPostId();
        return "redirect:/post/detail/" + postId;
    }

    @PostMapping("/comments/update/{id}")
    public String commentsUpdate(@PathVariable("id") Long id, CommentsDto commentsDto) throws Exception {
        commentsDto.setId(String.valueOf(id)); // 수정할 댓글의 ID 설정
        commentsService.update(commentsDto);
        Long postId = commentsService.findPostIdById(id); // 수정된 댓글이 속한 게시물의 ID 가져오기
        return "redirect:/post/detail/" + postId;
    }

    @GetMapping("/comments/delete")
    public String commentsDelete(@RequestParam(value = "id") Long id) {
        Long postId = commentsService.findPostIdById(id); // 댓글이 속한 게시물의 ID 가져오기
        commentsService.delete(id);
        return "redirect:/post/detail/" + postId;
    }




}

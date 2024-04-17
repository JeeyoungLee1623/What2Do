package com.What2Do.What2Do.post.controller;


import com.What2Do.What2Do.comment.domain.Comments;
import com.What2Do.What2Do.comment.service.CommentsService;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.repository.MemberRepository;

import com.What2Do.What2Do.post.domain.Post;
import com.What2Do.What2Do.post.etc.PostDto;
import com.What2Do.What2Do.post.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommentsService commentsService;


    // 게시글 쓰기
    @GetMapping("/post/new")
    public String showCreatePostForm(Model model) {
        // 현재 로그인된 사용자 정보를 가져온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member currentUser = memberRepository.findByUserName(authentication.getName());
        // 모델에 현재 사용자의 아이디를 추가한다.
        model.addAttribute("currentUserId", currentUser.getUserName());

        return "post/post-form";
    }

    @PostMapping("/post/new")
    public String createPost(PostDto postDto, RedirectAttributes redirectAttributes) throws Exception {
        postService.create(postDto);
        return "redirect:/post/lists";
    }



    @GetMapping("/post/lists")
    public String postFindAll(Model model) throws Exception{
        List<Post> posts = postService.findAll();
        model.addAttribute("postList", posts);
        return "post/post-list";
    }


    //게시글 보기
    @GetMapping("/post/detail/{id}")
    public String postFindById(@PathVariable(value = "id") Long myId,  Model model) throws Exception {
        Post post1 = postService.findById(myId);
        List<Comments> comments = commentsService.findAllByPostId(myId);
        model.addAttribute("commentsLists", comments);
        model.addAttribute("postDetail" , post1);
        return "post/post-detail";
    }

    // 게시글 수정
    @PostMapping("/post/update/{id}")
    public String postUpdate(PostDto postDto) throws Exception {
        postService.update(postDto);
        return "redirect:/post/lists";
    }

    // 게시글 삭제
    @GetMapping("/post/delete") // 포스트를 삭제하고 성공 여부를 반환하는 엔드포인트
    public String postDelete(@RequestParam(value = "id")String id) {
        postService.delete(Long.parseLong(id));
        return "redirect:/post/lists";
    }


    }

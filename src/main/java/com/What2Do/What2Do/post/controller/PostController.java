package com.What2Do.What2Do.post.controller;

import com.What2Do.What2Do.comment.repository.CommentRepository;
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
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;


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
    public String postFindById(@PathVariable(value = "id") Long myId, Model model) throws Exception {
        Post post1 = postService.findById(myId);
        model.addAttribute("postDetail" , post1);
    //        model.addAttribute("comments" , comments);
        return "post/post-detail";
    }

    // 게시글 수정
    @PostMapping("/post/update/{id}") // 포스트를 업데이트하고 성공 여부를 반환하는 엔드포인트
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

    @GetMapping("/post/search")
    public String search(String keyword, Model model, @PageableDefault(page=0, size=10, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
        //서비스에서 생성한 리스트를 list라는 이름으로 반환하겠다.
        Page<Post> searchList = postService.searchList(keyword, pageable);
        //페이지블럭 처리
        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
        int nowPage = searchList.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, searchList.getTotalPages());

        model.addAttribute("searchList", searchList);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/post/post-list";
    }

    }

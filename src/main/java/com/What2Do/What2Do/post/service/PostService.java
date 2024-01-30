package com.What2Do.What2Do.post.service;

import com.What2Do.What2Do.comment.repository.CommentRepository;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.repository.MemberRepository;
import com.What2Do.What2Do.post.domain.Post;
import com.What2Do.What2Do.post.etc.PostDto;
import com.What2Do.What2Do.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommentRepository commentRepository;

    public void create(PostDto postDto) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Member member1 = memberRepository.findByUserName(currentUsername);

        Post post1 = Post.builder()
                .category(postDto.getCategory())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .member(member1)
                .imageUrl(postDto.getImageUrl())
                .build();
        postRepository.save(post1);
    }

    public List<Post> findAll(){
        List<Post> post2 = postRepository.findAll();
        return post2;
    }


    public Post findById (Long myId){
        Post post = postRepository.findById(myId).orElseThrow(EntityExistsException::new);
        return post;
    }

    public void update (PostDto postDto) throws Exception {
        Post post3 = postRepository.findById(Long.parseLong(postDto.getId())).orElseThrow(Exception::new);
        post3.setCategory(postDto.getCategory());
        post3.setContent(postDto.getContent());
        post3.setTitle(postDto.getTitle());
        postRepository.save(post3);
    }

    public void delete(Long id){
        postRepository.delete(this.findById(id));
    }



    public List<Post> findPostsByPage(int page) {
        int pageSize = 10; // 페이지당 게시글 수
        int offset = (page - 1) * pageSize; // 페이지 시작 오프셋 계산

        // 데이터베이스에서 페이지에 해당하는 게시글을 조회
        return postRepository.findPostsByPage(page);
    }

    public int getPostCountByMemberId(Long memberId) {
        // memberId를 이용하여 해당 멤버의 게시글 수를 조회하는 로직을 구현합니다.
        int postCount = postRepository.countPostsByMemberId(memberId);
        return postCount;
    }

//    public Page<Post> getList(int page) {
//        List<Sort.Order> sorts = new ArrayList<>();
//        sorts.add(Sort.Order.desc("createDate"));
//        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//        return this.postRepository.findAll(pageable);
//    }

    @Transactional
    public Page<Post> searchList (String keyword, Pageable pageable){
        Page<Post>  postList = (Page<Post>) postRepository.findByTitleContaining(keyword, pageable);
        return  postList;
    }


}








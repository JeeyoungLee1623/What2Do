package com.What2Do.What2Do.comment.service;

import com.What2Do.What2Do.comment.domain.Comment;
import com.What2Do.What2Do.comment.etc.CommentDto;
import com.What2Do.What2Do.comment.repository.CommentRepository;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.service.MemberService;
import com.What2Do.What2Do.post.domain.Post;
import com.What2Do.What2Do.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PostService postService;



    public Comment create (CommentDto commentDto){
        Member member1 = memberService.findById(commentDto.getMember().getId());
        Post post1 = postService.findById(commentDto.getPost().getId());
        Comment comment1 = Comment.builder()
                .comment(commentDto.getComment())
                .member(member1)
                .post(post1)
                .build();
        commentRepository.save(comment1);
        return comment1;
    }

    public List<Comment> findAll(){
        List<Comment> comments = commentRepository.findAll();
        return comments;
    }


    public Comment findById(Long myId) throws Exception {
        Comment comment2 = commentRepository.findById(myId).orElseThrow(Exception::new);
        return comment2;
    }


    public Comment update (Long commentId, CommentDto commentDto) throws Exception {
        Comment comment3 = commentRepository.findById(Long.parseLong(commentDto.getId())).orElseThrow(Exception::new);
        comment3.setComment(commentDto.getComment());
        commentRepository.save(comment3);
        return comment3;
    }


    public Comment delete(Long id) throws Exception {
        commentRepository.delete(this.findById(id));
        return null;
    }


    public int getCommentCountByMemberId(Long memberId) {
        // memberId를 이용하여 해당 멤버의 댓글 수를 조회하는 로직을 구현합니다.
        int commentCount = commentRepository.countCommentsByMemberId(memberId);
        return commentCount;
    }









}


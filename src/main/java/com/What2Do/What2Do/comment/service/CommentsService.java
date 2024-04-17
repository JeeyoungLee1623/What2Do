package com.What2Do.What2Do.comment.service;


import com.What2Do.What2Do.comment.domain.Comments;
import com.What2Do.What2Do.comment.etc.CommentsDto;
import com.What2Do.What2Do.comment.repository.CommentsRepository;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.repository.MemberRepository;
import com.What2Do.What2Do.post.domain.Post;
import com.What2Do.What2Do.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentsService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostRepository postRepository;

    public void create (CommentsDto commentsDto) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Member member1 = memberRepository.findByUserName(currentUsername);
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new Exception("해당 ID의 포스트를 찾을 수 없습니다."));

        Comments comments = Comments.builder()
                .comments(commentsDto.getComments())
                .post(post)
                .member(member1)
                .build();
        commentsRepository.save(comments);
    }


    public List<Comments> findAllByPostId(Long postId){
        List<Comments> comments = commentsRepository.findAllByPostId(postId);
        return comments;
    }


    public Long findPostIdById (Long commentId) {
        Comments comment = commentsRepository.findById(commentId).orElse(null);
        if (comment != null) {
            return comment.getPost().getId();
        }
        return null;
    }

    public void update(CommentsDto commentsDto) throws Exception {
        Comments comments1 = commentsRepository.findById(Long.valueOf(commentsDto.getId())).orElseThrow(Exception::new);
        comments1.setComments(commentsDto.getComments()); // 수정된 내용 설정
        commentsRepository.save(comments1);
    }

    public void delete(Long id){
        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 댓글을 찾을 수 없습니다."));
        commentsRepository.delete(comment);
    }


}

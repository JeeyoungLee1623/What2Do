package com.What2Do.What2Do.comment.repository;

import com.What2Do.What2Do.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    int countCommentsByMemberId(Long memberId);
}

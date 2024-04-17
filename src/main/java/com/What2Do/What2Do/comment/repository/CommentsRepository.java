package com.What2Do.What2Do.comment.repository;


import com.What2Do.What2Do.comment.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {


    List<Comments> findAllByPostId(Long postId);

    Long findPostIdById (Long id);






}

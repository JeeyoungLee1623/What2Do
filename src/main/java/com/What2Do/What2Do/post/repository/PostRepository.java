package com.What2Do.What2Do.post.repository;

import com.What2Do.What2Do.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByPage(int page);
    int countPostsByMemberId(Long memberId);
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);



}

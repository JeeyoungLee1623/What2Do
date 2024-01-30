package com.What2Do.What2Do.comment.domain;

import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.post.domain.Post;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Comment (String comment, Post post, Member member){
        this.comment = comment;
        this.post = post;
        this.member = member;
        this.createDate = LocalDateTime.now();
    }








}



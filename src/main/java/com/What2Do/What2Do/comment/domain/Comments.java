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
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(nullable = false, name= "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @Column
    private LocalDateTime createDate;


    @Builder
    public Comments (String comments, Post post, Member member){
        this.comments = comments;
        this.post = post;
        this.member = member;
        this.createDate = LocalDateTime.now();
    }

}

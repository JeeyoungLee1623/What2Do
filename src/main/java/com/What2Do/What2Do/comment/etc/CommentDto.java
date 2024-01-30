package com.What2Do.What2Do.comment.etc;

import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String id;
    private String comment;

    private Post post;

    private Member member;


}


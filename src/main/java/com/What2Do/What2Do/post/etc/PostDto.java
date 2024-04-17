package com.What2Do.What2Do.post.etc;

import com.What2Do.What2Do.comment.domain.Comments;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.post.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {

    private String id;
    private Category category;
    private String title;
    private String content;
    private Member memberId;
    private String email;
    private String keyword;
    private String type;

    private List<Comments> comments;

}

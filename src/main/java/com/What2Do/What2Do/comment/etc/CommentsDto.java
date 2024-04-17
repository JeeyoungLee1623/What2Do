package com.What2Do.What2Do.comment.etc;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsDto {

    private String id;

    private String comments;

    private Long postId;

    private Long memberId;

}

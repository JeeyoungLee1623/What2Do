package com.What2Do.What2Do.infoComment.etc;

import com.What2Do.What2Do.information.domain.Information;
import com.What2Do.What2Do.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoCommentDto {

    private String id;
    private String comment;
    private Information information;
    private Member member;


}
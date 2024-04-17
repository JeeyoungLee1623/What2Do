package com.What2Do.What2Do.infoComment.etc;

import com.What2Do.What2Do.information.domain.Information;
import com.What2Do.What2Do.member.domain.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class InfoCommentDto {

    private String id;
    private String comment;
    private Long informationId;
    private Long memberId;


}
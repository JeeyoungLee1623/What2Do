package com.What2Do.What2Do.information.etc;

import com.What2Do.What2Do.infoComment.domain.InfoComment;
import com.What2Do.What2Do.information.domain.Category2;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InformationDto {

    private String id;
    private Category2 category;
    private String title;
    private String content;
    private List<InfoComment> infoComments;
    private String imageUrl;

}

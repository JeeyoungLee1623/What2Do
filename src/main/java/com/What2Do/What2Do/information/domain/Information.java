package com.What2Do.What2Do.information.domain;

import com.What2Do.What2Do.infoComment.domain.InfoComment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category2 category;

    @Column
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "information", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<InfoComment> infoComments;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime createDate;


    @Builder
    public Information (Category2 category, String title, String content, String imageUrl){
        this.category = Category2.클래스;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;

    }




}

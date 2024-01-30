package com.What2Do.What2Do.post.domain;

import com.What2Do.What2Do.comment.domain.Comment;
import com.What2Do.What2Do.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

    @Column
    private  int page;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime createDate;

    @Builder
    public Post(String title, String content, Member member, Category category, String imageUrl, int page){
        this.category = Category.질문;
        this.title = title;
        this.content = content;
        this.member = member;
        this.page = page;
        this.imageUrl = imageUrl;
        this.createDate = LocalDateTime.now();
    }

}


package com.What2Do.What2Do.post.domain;

import com.What2Do.What2Do.comment.domain.Comments;
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

    @Column
    private  int page;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comments> comments;

    @Column
    private LocalDateTime createDate;

    @Builder
    public Post(String title, String content, Member member, Category category, int page, List<Comments> comments){
        this.category = category;
        this.title = title;
        this.content = content;
        this.member = member;
        this.page = page;
        this.comments = comments;
        this.createDate = LocalDateTime.now();
    }

}


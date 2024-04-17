package com.What2Do.What2Do.infoComment.domain;

import com.What2Do.What2Do.information.domain.Information;
import com.What2Do.What2Do.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class InfoComment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "information_id")
    private Information information;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public InfoComment (String comment, Information information, Member member){
        this.comment = comment;
        this.information = information;
        this.member = member;
        this.createDate = LocalDateTime.now();
    }

}


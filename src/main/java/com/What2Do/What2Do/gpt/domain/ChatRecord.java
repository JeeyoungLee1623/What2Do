package com.What2Do.What2Do.gpt.domain;

import com.What2Do.What2Do.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ChatRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @Column(length = 1000)
    private String message;

    private LocalDateTime timestamp;


    @Builder
    public ChatRecord (){
        this.member = member;
        this.message = message;
        this.timestamp = LocalDateTime.now();

    }


}


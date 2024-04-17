package com.What2Do.What2Do.member.domain;

import com.What2Do.What2Do.infoComment.domain.InfoComment;
import com.What2Do.What2Do.post.domain.Post;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column
    private String password;

    @Column
    private String name;
    @Column(length = 255)
    private String mobileNumber;

    @Column(unique = true)
    private String email;
    @Column
    private int age;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String address;

    @Column(length = 255)
    private String job;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;



    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> postsNum;


    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<InfoComment> infoCommentsNum;


    @Column
    private LocalDateTime createDate;



    @Builder
    public Member (Long id, String userName, String password, String name, String mobileNumber, int age, String gender,
                   Role role, String address , String job, String email){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.gender = Gender.잘모름;
        this.address = address;
        this.job = job;
        this.role = role;
        this.createDate = LocalDateTime.now();

    }


    public void updateMember(String mobileNumber, String job, String email) {
        if (mobileNumber != null) {
            this.mobileNumber = mobileNumber;
        }
        if (job != null) {
            this.job = job;
        }
        if (email != null) {
            this.email = email;
        }
    }

}




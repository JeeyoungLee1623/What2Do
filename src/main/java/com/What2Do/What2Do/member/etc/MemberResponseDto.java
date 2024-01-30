package com.What2Do.What2Do.member.etc;

import com.What2Do.What2Do.member.domain.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private String userName;
    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private int age;
    private Gender gender;
    private String job;
    private Long postNum;
    private Long commentNum;
    private Long infoCommentNum;




}

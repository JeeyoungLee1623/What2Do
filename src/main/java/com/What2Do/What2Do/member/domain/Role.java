package com.What2Do.What2Do.member.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST"), ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String key;


}

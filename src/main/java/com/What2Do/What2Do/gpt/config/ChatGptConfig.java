// ChatGptConfig.java
package com.What2Do.What2Do.gpt.config;

import com.What2Do.What2Do.member.domain.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String API_KEY = "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";
    public static final String MODEL = "gpt-3.5-turbo";
    public static final Integer MAX_TOKEN = 1000;
    public static final Boolean STREAM = false;
    public static final Double TEMPERATURE = 0.4;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/chat/completions";


    public static final String ROLE = "user";

}
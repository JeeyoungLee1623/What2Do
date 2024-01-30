package com.What2Do.What2Do.gpt.controller;

import com.What2Do.What2Do.gpt.dto.ChatGptResponseDto;
import com.What2Do.What2Do.gpt.dto.QuestionRequestDto;
import com.What2Do.What2Do.gpt.service.ChatGptService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "http://www.localhost:8081", allowCredentials = "true")
public class ChatGptController {

    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/chat-gpt/question")
//    @PreAuthorize("hasRole('USER')")
    public String chatGpt() {
        return "chat/chatGpt";
    }


    @Operation(summary = "Question to Chat-GPT")
    @PostMapping("/chat-gpt/question")
    @ResponseBody
    public ResponseEntity<ChatGptResponseDto> sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        // 여기에서 사용자가 로그인한 상태인지 확인하고, 아니라면 에러 응답을 반환할 수 있습니다.
        // Spring Security의 SecurityContextHolder를 사용하여 현재 사용자의 인증 정보를 확인할 수 있습니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ChatGptResponseDto responseDto = chatGptService.askQuestion(requestDto);
        return ResponseEntity.ok(responseDto);


    }

}
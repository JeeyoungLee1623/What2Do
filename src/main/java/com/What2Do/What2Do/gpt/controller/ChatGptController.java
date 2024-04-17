package com.What2Do.What2Do.gpt.controller;

import com.What2Do.What2Do.gpt.dto.*;
import com.What2Do.What2Do.gpt.service.ChatGptService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@Controller
@CrossOrigin(origins = "http://www.localhost:8081", allowCredentials = "true")
public class ChatGptController {

    private final ChatGptService chatGptService;
    private final Map<String, String> conversationCache = new HashMap<>();

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
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        return chatGptService.askQuestion(requestDto);
    }

}
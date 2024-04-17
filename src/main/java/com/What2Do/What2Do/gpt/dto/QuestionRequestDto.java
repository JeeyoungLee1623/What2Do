package com.What2Do.What2Do.gpt.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class QuestionRequestDto implements Serializable {
    private String question;
    private List<ChatGptMessage> messages;

    private String UserResponses;

    public boolean isValid() {
        return messages != null && messages.stream()
                .allMatch(message -> message != null && message.getContent() != null);
    }

}
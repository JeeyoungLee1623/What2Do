package com.What2Do.What2Do.gpt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatGptResponseDto implements Serializable {

    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String answer;

    private List<ChatGptMessage> messages;


    @Builder
    public ChatGptResponseDto(String id, String object,
                              LocalDate created, String model,
                              List<Choice> choices,Usage usage, String answer, List<ChatGptMessage> messages) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
        this.answer = answer;
        this.messages = messages;
    }
    // 추가: GPT 응답에서 생성된 텍스트를 반환하는 메서드
    public String getText() {
        if (this.choices != null && !this.choices.isEmpty()) {
            return this.choices.get(0).getText();
        }
        return null;
    }
}

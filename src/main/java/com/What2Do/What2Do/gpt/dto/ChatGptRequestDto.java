package com.What2Do.What2Do.gpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptRequestDto implements Serializable {

    private String model;
    //    private String prompt;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;

    private Boolean stream;
    private List<ChatGptMessage> messages;

//    @JsonProperty("top_p")
//    private Double topP;

    @Builder
    public ChatGptRequestDto(String model,
                             Integer maxTokens, Double temperature,
                             Boolean stream, List<ChatGptMessage> messages) {
        this.model = model;
//        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.stream = stream;
        this.messages = messages;

//        this.topP = topP;
    }
}

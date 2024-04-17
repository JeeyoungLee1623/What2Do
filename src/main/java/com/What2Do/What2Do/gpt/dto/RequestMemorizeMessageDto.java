package com.What2Do.What2Do.gpt.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestMemorizeMessageDto {
    @NotNull
    @NotEmpty
    private String message;
}

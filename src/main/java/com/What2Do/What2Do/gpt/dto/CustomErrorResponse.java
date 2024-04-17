package com.What2Do.What2Do.gpt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponse {

    private String error;
    private String message;

    public CustomErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public static CustomErrorResponse createErrorResponse() {
        return new CustomErrorResponse("Invalid request", "The request contains invalid values");
    }
}
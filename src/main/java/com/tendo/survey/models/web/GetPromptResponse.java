package com.tendo.survey.models.web;

import lombok.Data;

import java.util.List;

@Data
public class GetPromptResponse {
    private String id;
    private String text;
    private String responseType;
    private List<Object> responseOptions;
}

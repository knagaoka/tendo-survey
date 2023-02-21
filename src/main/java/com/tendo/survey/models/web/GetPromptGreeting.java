package com.tendo.survey.models.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPromptGreeting {

    private String type;
    private String text;
}

package com.tendo.survey.models.web;

import lombok.Data;

@Data
public class GetPatientPromptAnswer {
    private String prompt;
    private String promptId;
    private Object answer;
    private String answerId;
}

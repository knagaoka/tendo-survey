package com.tendo.survey.models.web;

import lombok.Data;

import java.util.List;

@Data
public class GetSurveyPromptsResponse {
    private String surveyId;
    private List<GetPromptResponse> prompts;
}

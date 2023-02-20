package com.tendo.survey.models.web;

import lombok.Data;

import java.util.List;

@Data
public class GetPatientSurveyAnswers {
    private String patientSurveyId;
    private String surveyId;
    private List<GetPatientPromptAnswer> promptAnswers;
}

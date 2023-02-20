package com.tendo.survey.models.web;

import lombok.Data;

import java.util.List;

@Data
public class PutPatientAnswers {
    private String surveyId;
    private String patientDataId;
    private List<PutAnswer> answers;
}

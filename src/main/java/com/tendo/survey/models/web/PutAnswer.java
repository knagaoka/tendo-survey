package com.tendo.survey.models.web;

import lombok.Data;

@Data
public class PutAnswer {
    private String promptId;
    private Object answer;
}

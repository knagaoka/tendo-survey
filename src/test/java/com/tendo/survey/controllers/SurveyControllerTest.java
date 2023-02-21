package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.Survey;
import com.tendo.survey.repositories.SurveyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class SurveyControllerTest {

    @Spy
    private SurveyRepository surveyRepositoryMock;

    private SurveyController surveyController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        surveyController = new SurveyController(surveyRepositoryMock);
    }

    @Test
    public void getTest() {
        UUID uuid = UUID.randomUUID();
        Survey survey = new Survey();
        survey.setId(uuid);
        survey.setTenant("tendo");
        doReturn(Optional.of(survey)).when(surveyRepositoryMock).findById(any(UUID.class));

        ResponseEntity<Survey> response = surveyController.get(uuid.toString());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

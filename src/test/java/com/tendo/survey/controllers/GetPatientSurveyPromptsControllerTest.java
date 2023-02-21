package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.Prompt;
import com.tendo.survey.models.dao.PromptDialogMapping;
import com.tendo.survey.models.dao.PromptResponseType;
import com.tendo.survey.models.dao.Survey;
import com.tendo.survey.models.web.GetSurveyPromptsRequest;
import com.tendo.survey.models.web.GetSurveyPromptsResponse;
import com.tendo.survey.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

public class GetPatientSurveyPromptsControllerTest {

    private static final String MOCK_DATA = "{\"resourceType\":\"Bundle\",\"id\":\"0c3151bd-1cbf-4d64-b04d-cd9187a4c6e0\",\"timestamp\":\"2021-04-02T12:12:21Z\",\"entry\":[{\"resource\":{\"resourceType\":\"Patient\",\"id\":\"6739ec3e-93bd-11eb-a8b3-0242ac130003\",\"active\":true,\"name\":[{\"text\":\"TendoTenderson\",\"family\":\"Tenderson\",\"given\":[\"Tendo\"]}],\"contact\":[{\"system\":\"phone\",\"value\":\"555-555-2021\",\"use\":\"mobile\"},{\"system\":\"email\",\"value\":\"tendo@tendoco.com\",\"use\":\"work\"}],\"gender\":\"female\",\"birthDate\":\"1955-01-06\",\"address\":[{\"use\":\"home\",\"line\":[\"2222HomeStreet\"]}]}},{\"resource\":{\"resourceType\":\"Doctor\",\"id\":\"9bf9e532-93bd-11eb-a8b3-0242ac130003\",\"name\":[{\"family\":\"Careful\",\"given\":[\"Adam\"]}]}},{\"resource\":{\"resourceType\":\"Appointment\",\"id\":\"be142dc6-93bd-11eb-a8b3-0242ac130003\",\"status\":\"finished\",\"type\":[{\"text\":\"Endocrinologistvisit\"}],\"subject\":{\"reference\":\"Patient/6739ec3e-93bd-11eb-a8b3-0242ac130003\"},\"actor\":{\"reference\":\"Doctor/9bf9e532-93bd-11eb-a8b3-0242ac130003\"},\"period\":{\"start\":\"2021-04-02T11:30:00Z\",\"end\":\"2021-04-02T12:00:00Z\"}}},{\"resource\":{\"resourceType\":\"Diagnosis\",\"id\":\"541a72a8-df75-4484-ac89-ac4923f03b81\",\"meta\":{\"lastUpdated\":\"2021-04-02T11:51:03Z\"},\"status\":\"final\",\"code\":{\"coding\":[{\"system\":\"http://hl7.org/fhir/sid/icd-10\",\"code\":\"E10-E14.9\",\"name\":\"Diabeteswithoutcomplications\"}]},\"appointment\":{\"reference\":\"Appointment/be142dc6-93bd-11eb-a8b3-0242ac130003\"}}}]}";

    @Spy
    private SurveyRepository surveyRepositoryMock;

    @Spy
    private PromptRepository promptRepositoryMock;

    @Spy
    private PromptDialogMappingRepository promptDialogMappingRepositoryMock;

    @Spy
    private PromptResponseTypeRepository promptResponseTypeRepositoryMock;

    @Spy
    private PatientDataRepository patientDataRepositoryMock;

    private GetPatientSurveyPromptsController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new GetPatientSurveyPromptsController(
                surveyRepositoryMock,
                promptRepositoryMock,
                promptDialogMappingRepositoryMock,
                promptResponseTypeRepositoryMock,
                patientDataRepositoryMock
        );
    }

    @Test
    public void getTest() {
        GetSurveyPromptsRequest request = new GetSurveyPromptsRequest();
        request.setId(UUID.randomUUID().toString());
        request.setJson(MOCK_DATA);

        Survey survey = new Survey();
        survey.setId(UUID.randomUUID());
        doReturn(Optional.of(survey)).when(surveyRepositoryMock).findById(any(UUID.class));

        Prompt prompt = new Prompt();
        prompt.setId(UUID.randomUUID());
        prompt.setText("Hello");
        doReturn(List.of(prompt)).when(promptRepositoryMock).findBySurveyId(anyString());

        PromptResponseType promptResponseType = new PromptResponseType();
        doReturn(promptResponseType).when(promptResponseTypeRepositoryMock).findByPromptId(anyString());

        PromptDialogMapping promptDialogMapping = new PromptDialogMapping();
        promptDialogMapping.setMappings(Map.of());
        doReturn(promptDialogMapping).when(promptDialogMappingRepositoryMock).findByPromptId(anyString());

        ResponseEntity<GetSurveyPromptsResponse> response =  controller.post(request);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

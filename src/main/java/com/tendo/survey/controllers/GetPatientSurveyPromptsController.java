package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.*;
import com.tendo.survey.models.web.GetPromptResponse;
import com.tendo.survey.models.web.GetSurveyPromptsRequest;
import com.tendo.survey.models.web.GetSurveyPromptsResponse;
import com.tendo.survey.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class GetPatientSurveyPromptsController {

    private final SurveyRepository surveyRepository;
    private final PromptRepository promptRepository;
    private final PromptDialogMappingRepository promptDialogMappingRepository;
    private final PromptResponseTypeRepository promptResponseTypeRepository;
    private final PatientDataRepository patientDataRepository;

    @Autowired
    public GetPatientSurveyPromptsController(
            SurveyRepository surveyRepository,
            PromptRepository promptRepository,
            PromptDialogMappingRepository promptDialogMappingRepository,
            PromptResponseTypeRepository promptResponseTypeRepository,
            PatientDataRepository patientDataRepository) {
        this.surveyRepository = surveyRepository;
        this.promptRepository = promptRepository;
        this.promptDialogMappingRepository = promptDialogMappingRepository;
        this.promptResponseTypeRepository = promptResponseTypeRepository;
        this.patientDataRepository = patientDataRepository;
    }

    @PostMapping(path = "/patientSurveyPrompts")
    public @ResponseBody ResponseEntity<GetSurveyPromptsResponse> post(@RequestBody GetSurveyPromptsRequest getSurveyPromptsRequest) {
        Optional<Survey> surveyOpt = surveyRepository.findById(UUID.fromString(getSurveyPromptsRequest.getId()));
        if (surveyOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Survey survey = surveyOpt.get();
        GetSurveyPromptsResponse getSurveyPromptsResponse = getGetSurveyPromptsResponse(survey, getSurveyPromptsRequest.getJson());

        return new ResponseEntity<>(getSurveyPromptsResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/patientSurveyPrompts/{surveyId}/{bundleId}")
    public @ResponseBody ResponseEntity<GetSurveyPromptsResponse> get(@PathVariable String surveyId, @PathVariable String bundleId) {
        Optional<Survey> surveyOpt = surveyRepository.findById(UUID.fromString(surveyId));
        if (surveyOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<PatientData> patientDataOpt = patientDataRepository.findById(UUID.fromString(bundleId));
        if (patientDataOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Survey survey = surveyOpt.get();
        PatientData patientData = patientDataOpt.get();
        GetSurveyPromptsResponse getSurveyPromptsResponse = getGetSurveyPromptsResponse(survey, patientData.dataString());

        return new ResponseEntity<>(getSurveyPromptsResponse, HttpStatus.OK);
    }

    private GetSurveyPromptsResponse getGetSurveyPromptsResponse(Survey survey, String json) {
        List<Prompt> prompts = promptRepository.getPrompts(survey.getId().toString());
        List<GetPromptResponse> getPromptResponses = prompts.stream()
                .map(prompt -> getGetPromptResponse(prompt, json))
                .collect(Collectors.toList());

        GetSurveyPromptsResponse getSurveyPromptsResponse = new GetSurveyPromptsResponse();
        getSurveyPromptsResponse.setSurveyId(survey.getId().toString());
        getSurveyPromptsResponse.setPrompts(getPromptResponses);
        return getSurveyPromptsResponse;
    }

    private GetPromptResponse getGetPromptResponse(Prompt prompt, String json) {
        String promptId = prompt.getId().toString();
        PromptResponseType promptResponseType = promptResponseTypeRepository.getPromptResponseType(promptId);
        PromptDialogMapping promptDialogMapping = promptDialogMappingRepository.getPromptDialogMapping(promptId);
        String text = prompt.templateToText(json, promptDialogMapping.getMappings());
        GetPromptResponse getPromptResponse = new GetPromptResponse();
        getPromptResponse.setId(promptId);
        getPromptResponse.setResponseType(promptResponseType.getType());
        List<Object> options = promptResponseType.getOptions() == null ?
                List.of() :
                (List<Object>) promptResponseType.getOptions().get("list");
        getPromptResponse.setResponseOptions(options);
        getPromptResponse.setText(text);
        return getPromptResponse;
    }
}

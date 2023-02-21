package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.*;
import com.tendo.survey.models.web.GetPatientPromptAnswer;
import com.tendo.survey.models.web.GetPatientSurveyAnswers;
import com.tendo.survey.models.web.GetPromptGreeting;
import com.tendo.survey.models.web.PutPatientAnswers;
import com.tendo.survey.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PatientAnswerController {

    private final PatientSurveyRepository patientSurveyRepository;
    private final PatientAnswerRepository patientAnswerRepository;
    private final PatientDataRepository patientDataRepository;
    private final PromptRepository promptRepository;
    private final PromptDialogMappingRepository promptDialogMappingRepository;
    private final PromptResponseTypeRepository promptResponseTypeRepository;
    private final PromptGreetingRepository promptGreetingRepository;
    private final SurveyRepository surveyRepository;

    @Autowired
    public PatientAnswerController(
            PatientSurveyRepository patientSurveyRepository,
            PatientAnswerRepository patientAnswerRepository,
            PatientDataRepository patientDataRepository,
            PromptRepository promptRepository,
            PromptDialogMappingRepository promptDialogMappingRepository,
            PromptResponseTypeRepository promptResponseTypeRepository,
            PromptGreetingRepository promptGreetingRepository,
            SurveyRepository surveyRepository
    ) {
        this.patientSurveyRepository = patientSurveyRepository;
        this.patientAnswerRepository = patientAnswerRepository;
        this.patientDataRepository = patientDataRepository;
        this.promptRepository = promptRepository;
        this.promptDialogMappingRepository = promptDialogMappingRepository;
        this.promptResponseTypeRepository = promptResponseTypeRepository;
        this.promptGreetingRepository = promptGreetingRepository;
        this.surveyRepository = surveyRepository;
    }

    @PostMapping(path = "/patientAnswers")
    public @ResponseBody ResponseEntity<PatientSurvey> post(@RequestBody PutPatientAnswers putPatientAnswers, @RequestHeader String userId) {
        PatientSurvey newPatientSurvey = new PatientSurvey();
        newPatientSurvey.setSurveyId(putPatientAnswers.getSurveyId());
        newPatientSurvey.setUserId(userId);
        newPatientSurvey.setPatientDataId(putPatientAnswers.getPatientDataId());
        PatientSurvey patientSurvey = patientSurveyRepository.save(newPatientSurvey);

        putPatientAnswers.getAnswers().forEach(putPatientAnswer -> {
            PatientAnswer newPatientAnswer = new PatientAnswer();
            newPatientAnswer.setPatientSurveyId(patientSurvey.getId().toString());
            newPatientAnswer.setUserId(userId);
            newPatientAnswer.setPromptId(putPatientAnswer.getPromptId());
            Map<String, Object> answerValue = new HashMap<>();
            PromptResponseType promptResponseType = promptResponseTypeRepository.getPromptResponseType(putPatientAnswer.getPromptId());
            answerValue.put(promptResponseType.getType(), putPatientAnswer.getAnswer());
            newPatientAnswer.setValue(answerValue);
            patientAnswerRepository.save(newPatientAnswer);
        });

        return new ResponseEntity<>(patientSurvey, HttpStatus.OK);
    }

    @GetMapping(path = "/patientAnswers/{patientSurveyId}/{patientDataId}")
    public @ResponseBody ResponseEntity<GetPatientSurveyAnswers> get(@PathVariable String patientSurveyId, @PathVariable String patientDataId) {
        Optional<PatientSurvey> patientSurveyOpt = patientSurveyRepository.findById(UUID.fromString(patientSurveyId));
        if (patientSurveyOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        PatientSurvey patientSurvey = patientSurveyOpt.get();
        Optional<Survey> surveyOpt = surveyRepository.findById(UUID.fromString(patientSurvey.getSurveyId()));
        if (surveyOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Survey survey = surveyOpt.get();
        Optional<PatientData> patientDataOpt = patientDataRepository.findById(UUID.fromString(patientDataId));
        if (patientDataOpt.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        PatientData patientData = patientDataOpt.get();
        List<Prompt> prompts = promptRepository.getPrompts(survey.getId().toString());
        List<GetPatientPromptAnswer> getPatientPromptAnswers = prompts.stream()
                .map(prompt -> getGetPatientPromptAnswer(prompt, patientData.dataString(), patientSurveyId))
                .collect(Collectors.toList());
        List<GetPromptGreeting> getPromptGreetings = promptGreetingRepository.getPromptGreetings(survey.getId().toString()).stream()
                .map(promptGreeting -> new GetPromptGreeting(promptGreeting.getType(), promptGreeting.getText()))
                .collect(Collectors.toList());

        GetPatientSurveyAnswers getPatientSurveyAnswers = new GetPatientSurveyAnswers();
        getPatientSurveyAnswers.setPatientSurveyId(patientSurvey.getId().toString());
        getPatientSurveyAnswers.setSurveyId(patientSurvey.getSurveyId());
        getPatientSurveyAnswers.setPromptAnswers(getPatientPromptAnswers);
        getPatientSurveyAnswers.setPromptGreetings(getPromptGreetings);

        return new ResponseEntity<>(getPatientSurveyAnswers, HttpStatus.OK);
    }

    private GetPatientPromptAnswer getGetPatientPromptAnswer(Prompt prompt, String json, String patientSurveyId) {
        String promptId = prompt.getId().toString();
        PromptDialogMapping promptDialogMapping = promptDialogMappingRepository.getPromptDialogMapping(promptId);
        String text = prompt.templateToText(json, promptDialogMapping.getMappings());
        PatientAnswer patientAnswer = patientAnswerRepository.getPatientAnswer(prompt.getId().toString(), patientSurveyId);

        GetPatientPromptAnswer getPatientPromptAnswer = new GetPatientPromptAnswer();
        getPatientPromptAnswer.setPrompt(text);
        getPatientPromptAnswer.setPromptId(prompt.getId().toString());
        getPatientPromptAnswer.setAnswer(patientAnswer.getValue());
        getPatientPromptAnswer.setAnswerId(patientAnswer.getId().toString());

        return getPatientPromptAnswer;
    }
}

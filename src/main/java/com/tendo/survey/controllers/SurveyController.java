package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.Survey;
import com.tendo.survey.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class SurveyController {

    private final SurveyRepository repository;

    @Autowired
    public SurveyController(SurveyRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/survey/{id}")
    public @ResponseBody ResponseEntity<Survey> get(@PathVariable String id) {
        return repository.findById(UUID.fromString(id))
                .map(survey -> new ResponseEntity<>(survey, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/survey")
    public @ResponseBody ResponseEntity<Survey> post(@RequestBody Survey newSurvey) {
        Survey survey = repository.save(newSurvey);
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }
}

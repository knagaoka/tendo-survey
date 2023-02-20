package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.PromptResponseType;
import com.tendo.survey.repositories.PromptResponseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptResponseTypeController {

    private final PromptResponseTypeRepository repository;

    @Autowired
    public PromptResponseTypeController(PromptResponseTypeRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/promptResponseType")
    public @ResponseBody ResponseEntity<PromptResponseType> post(@RequestBody PromptResponseType newPromptResponseType) {
        PromptResponseType promptResponseType = repository.save(newPromptResponseType);
        return new ResponseEntity<>(promptResponseType, HttpStatus.OK);
    }
}

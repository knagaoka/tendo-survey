package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.Prompt;
import com.tendo.survey.repositories.PromptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptController {

    private final PromptRepository repository;

    @Autowired
    public PromptController(PromptRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/prompt")
    public @ResponseBody ResponseEntity<Prompt> post(@RequestBody Prompt newPrompt) {
        Prompt prompt = repository.save(newPrompt);
        return new ResponseEntity<>(prompt, HttpStatus.OK);
    }
}

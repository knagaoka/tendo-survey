package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.PromptGreeting;
import com.tendo.survey.repositories.PromptGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptGreetingController {

    private final PromptGreetingRepository promptGreetingRepository;

    @Autowired
    public PromptGreetingController(PromptGreetingRepository promptGreetingRepository) {
        this.promptGreetingRepository = promptGreetingRepository;
    }

    @PostMapping(path = "/promptGreeting")
    public @ResponseBody ResponseEntity<PromptGreeting> post(@RequestBody PromptGreeting newPromptGreeting) {
        PromptGreeting promptGreeting = promptGreetingRepository.save(newPromptGreeting);
        return new ResponseEntity<>(promptGreeting, HttpStatus.OK);
    }
}

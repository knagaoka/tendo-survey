package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.PromptDialogMapping;
import com.tendo.survey.repositories.PromptDialogMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptDialogMappingController {

    private final PromptDialogMappingRepository repository;

    @Autowired
    public PromptDialogMappingController(PromptDialogMappingRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/promptDialogMapping")
    public @ResponseBody ResponseEntity<PromptDialogMapping> post(@RequestBody PromptDialogMapping newPromptDialogMapping) {
        PromptDialogMapping promptDialogMapping = repository.save(newPromptDialogMapping);
        return new ResponseEntity<>(promptDialogMapping, HttpStatus.OK);
    }
}

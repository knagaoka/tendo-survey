package com.tendo.survey.controllers;

import com.tendo.survey.models.dao.PatientData;
import com.tendo.survey.models.web.PutPatientData;
import com.tendo.survey.repositories.PatientDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientDataController {

    private final PatientDataRepository patientDataRepository;

    @Autowired
    public PatientDataController(PatientDataRepository patientDataRepository) {
        this.patientDataRepository = patientDataRepository;
    }

    @PostMapping("/patientData")
    public ResponseEntity<PatientData> post(@RequestBody PutPatientData putPatientData) {
        PatientData newPatientData = new PatientData();
        newPatientData.setData(putPatientData.getData());
        PatientData patientData = patientDataRepository.save(newPatientData);
        return new ResponseEntity<>(patientData, HttpStatus.OK);
    }
}

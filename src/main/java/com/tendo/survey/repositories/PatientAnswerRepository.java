package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PatientAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PatientAnswerRepository extends CrudRepository<PatientAnswer, UUID> {

    default PatientAnswer getPatientAnswer(String promptId, String patientSurveyId) {
        for (PatientAnswer patientAnswer: findAll()) {
            if (patientAnswer.getPromptId().equals(promptId) && patientAnswer.getPatientSurveyId().equals(patientSurveyId)) {
                return patientAnswer;
            }
        }
        return null;
    }
}

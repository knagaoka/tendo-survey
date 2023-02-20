package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PatientSurvey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PatientSurveyRepository extends CrudRepository<PatientSurvey, UUID> {
}

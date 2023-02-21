package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PromptGreeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface PromptGreetingRepository extends CrudRepository<PromptGreeting, UUID> {

    List<PromptGreeting> findBySurveyId(String surveyId);
}

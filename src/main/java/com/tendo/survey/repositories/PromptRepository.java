package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.Prompt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface PromptRepository extends CrudRepository<Prompt, UUID> {

    List<Prompt> findBySurveyId(String surveyId);
}

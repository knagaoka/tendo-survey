package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.Prompt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RepositoryRestResource
public interface PromptRepository extends CrudRepository<Prompt, UUID> {

    default List<Prompt> getPrompts(String surveyId) {
        return StreamSupport.stream(findAll().spliterator(), false)
                .filter(prompt -> prompt.getSurveyId().equals(surveyId))
                .collect(Collectors.toList());
    }
}

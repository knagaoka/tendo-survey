package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PromptGreeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RepositoryRestResource
public interface PromptGreetingRepository extends CrudRepository<PromptGreeting, UUID> {

    default List<PromptGreeting> getPromptGreetings(String surveyId) {
        return StreamSupport.stream(findAll().spliterator(), false)
                .filter(promptGreeting -> promptGreeting.getSurveyId().equals(surveyId))
                .collect(Collectors.toList());
    }
}

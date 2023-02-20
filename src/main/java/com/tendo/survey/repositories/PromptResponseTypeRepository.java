package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PromptResponseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PromptResponseTypeRepository extends CrudRepository<PromptResponseType, UUID> {

    default PromptResponseType getPromptResponseType(String promptId) {
        for (PromptResponseType promptResponseType: findAll()) {
            if (promptResponseType.getPromptId().equals(promptId)) {
                return promptResponseType;
            }
        }
        return null;
    }
}

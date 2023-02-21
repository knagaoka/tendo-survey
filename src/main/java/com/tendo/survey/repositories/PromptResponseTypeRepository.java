package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PromptResponseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PromptResponseTypeRepository extends CrudRepository<PromptResponseType, UUID> {

    PromptResponseType findByPromptId(String promptId);
}

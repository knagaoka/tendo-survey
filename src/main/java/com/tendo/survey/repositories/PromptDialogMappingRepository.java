package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PromptDialogMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PromptDialogMappingRepository extends CrudRepository<PromptDialogMapping, UUID> {

    PromptDialogMapping findByPromptId(String promptId);
}

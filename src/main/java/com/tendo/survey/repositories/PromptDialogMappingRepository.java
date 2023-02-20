package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PromptDialogMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PromptDialogMappingRepository extends CrudRepository<PromptDialogMapping, UUID> {

    default PromptDialogMapping getPromptDialogMapping(String promptId) {
        for(PromptDialogMapping promptDialogMapping: findAll()) {
            if (promptDialogMapping.getPromptId().equals(promptId)) {
                return promptDialogMapping;
            }
        }
        return null;
    }
}

package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.Prompt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RepositoryRestResource
public interface PromptRepository extends CrudRepository<Prompt, UUID> {

    default List<Prompt> getPrompts(List<String> ids) {
        List<UUID> uuids = ids.stream().map(UUID::fromString).collect(Collectors.toList());
        List<Prompt> prompts = new ArrayList<>();
        findAllById(uuids).forEach(prompts::add);
        return prompts;
    }
}

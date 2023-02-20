package com.tendo.survey.models.dao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "prompt_response_type")
public class PromptResponseType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "prompt_id", nullable = false)
    private String promptId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "options", nullable = true)
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    private Map<String, Object> options;
}

package com.tendo.survey.models.dao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "prompt_dialog_mapping")
public class PromptDialogMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "prompt_id", nullable = false)
    private String promptId;

    @Column(name = "mappings", nullable = true)
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    private Map<String, String> mappings;
}

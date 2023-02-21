package com.tendo.survey.models.dao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "prompt_greeting")
public class PromptGreeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "survey_id", nullable = false)
    private String surveyId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "text", nullable = false)
    private String text;
}

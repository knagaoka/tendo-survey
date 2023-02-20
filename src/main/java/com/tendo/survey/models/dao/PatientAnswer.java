package com.tendo.survey.models.dao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "patient_answer")
public class PatientAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "prompt_id", nullable = false)
    private String promptId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "patient_survey_id", nullable = false)
    private String patientSurveyId;

    @Column(name = "value", nullable = true)
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    private Map<String, Object> value;
}

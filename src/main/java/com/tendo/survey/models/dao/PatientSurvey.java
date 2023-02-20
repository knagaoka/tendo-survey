package com.tendo.survey.models.dao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "patient_survey")
public class PatientSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "patient_data_id", nullable = false)
    private String patientDataId;

    @Column(name = "survey_id", nullable = false)
    private String surveyId;
}

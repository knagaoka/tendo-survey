package com.tendo.survey.models.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "patient_data")
public class PatientData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "data", nullable = false)
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    private Map<String, Object> data;

    public String dataString() {
        try {
            return new ObjectMapper().writeValueAsString(data);
        } catch (Exception e) {
            return "";
        }
    }
}

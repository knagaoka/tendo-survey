package com.tendo.survey.models.dao;

import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "prompt")
public class Prompt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "survey_id", nullable = false)
    private String surveyId;

    @Column(name = "text", nullable = false)
    private String text;

    /**
     * Replaces the templated text with the appropriate mapped fields.
     * @param json      the patient data json
     * @param mappings  the mapped fields to replace
     * @return          the prompt with the replaced fields
     */
    public String templateToText(String json, Map<String, String> mappings) {
        // need to use string buffer here since strings are immutable
        StringBuffer buffer = new StringBuffer(this.text);
        mappings.forEach((k, v) -> {
            String replacement = JsonPath.parse(json).read(v);
            int start = buffer.indexOf(k);
            int end = start + k.length();
            buffer.replace(start, end, "");
            buffer.insert(start, replacement);
        });
        return buffer.toString();
    }
}

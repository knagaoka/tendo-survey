package com.tendo.survey.models.dao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "tenant", nullable = false)
    private String tenant;

    @Column(name = "prompt_ids", nullable = false)
    @Type(type = "io.hypersistence.utils.hibernate.type.array.StringArrayType")
    private String[] promptIds;
}

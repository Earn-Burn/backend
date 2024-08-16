package com.fide.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.PrimitiveIterator;

@Document("events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    private String id;
    private String code;
    private String description;
    private Typologie typologie;
    private String produit;
    private Integer points;
    private List<Integer> conditionCode;
}

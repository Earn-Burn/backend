package com.fide.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("conditions")
@AllArgsConstructor
@NoArgsConstructor
public class Condition {
    @Id
    private String id;
    private Integer code;
    private String type;
    private String label;
    private String description;
}

package com.example.offers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "offers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @Id
    private String id;
    private String title;
    private String description;
    private Integer requiredPoints;
    private String imageBase64; // Stocke l'image en Base64
//    private List<String> userIds;
}

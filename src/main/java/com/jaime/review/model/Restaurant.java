package com.jaime.review.model;

import com.fasterxml.jackson.annotation.JsonFormat; // Import for JSON formatting
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String zipcode;  // Zip code of the restaurant location

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00") // Line added for formatting
    private Double peanutScore;  // Average score for peanut allergy

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00") // Line added for formatting
    private Double eggScore;     // Average score for egg allergy

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00") // Line added for formatting
    private Double dairyScore;   // Average score for dairy allergy

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT, pattern = "0.00") // Line added for formatting
    private Double overallScore; // Average score across all categories
}

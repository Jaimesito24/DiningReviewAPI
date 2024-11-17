package com.jaime.review.model;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String displayName;  // Unique display name for the user

    private String city;
    private String state;
    private String zipcode;

    private Boolean interestedInPeanutAllergy;  // True if interested in peanut allergies
    private Boolean interestedInEggAllergy;     // True if interested in egg allergies
    private Boolean interestedInDairyAllergy;   // True if interested in dairy allergies
}

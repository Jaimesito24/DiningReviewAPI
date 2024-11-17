package com.jaime.review.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userDisplayName;  // User's unique display name (updated from 'displayName')

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;  // Associated restaurant, represented by its ID

    @Column
    private Integer peanutScore;  // Optional score for peanut allergy, 1-5

    @Column
    private Integer eggScore;     // Optional score for egg allergy, 1-5

    @Column
    private Integer dairyScore;   // Optional score for dairy allergy, 1-5

    @Column(length = 1000)
    private String commentary;    // Optional commentary with a length constraint

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus status = ReviewStatus.PENDING; // Status of the review
}

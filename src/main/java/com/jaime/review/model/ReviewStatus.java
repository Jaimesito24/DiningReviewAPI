package com.jaime.review.model;

public enum ReviewStatus {
    PENDING,   // When the review is first submitted and awaiting admin action
    APPROVED,  // When an admin has approved the review
    REJECTED   // When an admin has rejected the review
}
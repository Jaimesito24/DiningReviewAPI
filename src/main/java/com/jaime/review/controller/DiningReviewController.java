package com.jaime.review.controller;

import com.jaime.review.model.DiningReview;
import com.jaime.review.request.AdminReviewAction;
import com.jaime.review.service.DiningReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class DiningReviewController {

    @Autowired
    private DiningReviewService diningReviewService;

    // Submits a new dining review
    @PostMapping
    public DiningReview submitReview(@RequestBody DiningReview diningReview) {
        
        if (diningReview == null || diningReview.getRestaurant() == null || diningReview.getUserDisplayName() == null) {
            throw new IllegalArgumentException("Dining review, restaurant reference, and user display name are required.");
        }

        // Assigns the task to the service layer.
        return diningReviewService.submitReview(diningReview);
    }

    // Gets all pending dining reviews
    @GetMapping("/pending")
    public List<DiningReview> getPendingReviews() {
        // Service handles fetching pending reviews
        return diningReviewService.getPendingReviews();
    }

    // Approves or rejects a dining review
    @PutMapping("/{reviewId}/action")
    public DiningReview approveOrRejectReview(
            @PathVariable Long reviewId,
            @RequestBody AdminReviewAction action) {
        // Manual validation for AdminReviewAction
        if (action == null || action.getAcceptReview() == null) {
            throw new IllegalArgumentException("Admin review action must include a valid accept/reject decision.");
        }

        
        return diningReviewService.approveOrRejectReview(reviewId, action.getAcceptReview());
    }

    // Gets all approved dining reviews for a specific restaurant
    @GetMapping("/approved/{restaurantId}")
    public List<DiningReview> getApprovedReviewsByRestaurant(@PathVariable Long restaurantId) {
        // Delegate to service layer
        return diningReviewService.getApprovedReviewsByRestaurant(restaurantId);
    }
}

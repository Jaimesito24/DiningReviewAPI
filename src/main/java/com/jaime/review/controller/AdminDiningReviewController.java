package com.jaime.review.controller;

import com.jaime.review.model.DiningReview;
import com.jaime.review.request.AdminReviewAction;
import com.jaime.review.service.DiningReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reviews")
public class AdminDiningReviewController {

    @Autowired
    private DiningReviewService diningReviewService;

    // Approve or reject a dining review
    @PutMapping("/{reviewId}/action")
    public DiningReview approveOrRejectReview(
            @PathVariable Long reviewId,
            @RequestBody AdminReviewAction action) {
        // Manual validation for AdminReviewAction
        if (action == null || action.getAcceptReview() == null) {
            throw new IllegalArgumentException("Admin review action must include a valid accept/reject decision.");
        }

        // Delegate to the service layer for approval/rejection
        return diningReviewService.approveOrRejectReview(reviewId, action.getAcceptReview());
    }

    // Fetch all pending dining reviews for admin review
    @GetMapping("/pending")
    public List<DiningReview> getPendingReviews() {
        // Delegate to the service layer for fetching pending reviews
        return diningReviewService.getPendingReviews();
    }
}

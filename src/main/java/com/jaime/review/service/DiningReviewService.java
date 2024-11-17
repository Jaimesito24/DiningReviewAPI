package com.jaime.review.service;

import com.jaime.review.model.DiningReview;
import com.jaime.review.model.Restaurant;
import com.jaime.review.model.ReviewStatus;
import com.jaime.review.repository.DiningReviewRepository;
import com.jaime.review.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiningReviewService {

    @Autowired
    private DiningReviewRepository diningReviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService; // Inject RestaurantService for score re-computation

    public DiningReview submitReview(DiningReview diningReview) {
        // Validate that the restaurant exists
        Long restaurantId = diningReview.getRestaurant().getId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant with ID " + restaurantId + " does not exist"));

        // Set the restaurant reference and initial review status
        diningReview.setRestaurant(restaurant);
        diningReview.setStatus(ReviewStatus.PENDING);
        return diningReviewRepository.save(diningReview);
    }

    public List<DiningReview> getPendingReviews() {
        // Fetch all reviews with PENDING status
        return diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    public DiningReview approveOrRejectReview(Long reviewId, Boolean acceptReview) {
        // Fetch the review by ID
        DiningReview review = diningReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        // Update the review's status based on the admin action
        review.setStatus(acceptReview ? ReviewStatus.APPROVED : ReviewStatus.REJECTED);
        DiningReview updatedReview = diningReviewRepository.save(review);

        // If the review is approved, re-compute the associated restaurant's scores
        if (acceptReview) {
            restaurantService.recomputeRestaurantScores(review.getRestaurant().getId());
        }

        return updatedReview;
    }

    public List<DiningReview> getApprovedReviewsByRestaurant(Long restaurantId) {
        // Validate that the restaurant exists
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        // Fetch all approved reviews for the given restaurant
        return diningReviewRepository.findByRestaurantAndStatus(restaurant, ReviewStatus.APPROVED);
    }
}

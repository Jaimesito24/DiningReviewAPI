package com.jaime.review.repository;

import com.jaime.review.model.DiningReview;
import com.jaime.review.model.Restaurant;
import com.jaime.review.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {

    /**
     * Finds all dining reviews with a specific status.
     *
     * @param status The status of the dining reviews to find (e.g., PENDING, APPROVED, REJECTED).
     * @return A list of dining reviews matching the given status.
     */
    List<DiningReview> findByStatus(ReviewStatus status);

    /**
     * Finds all dining reviews for a specific restaurant with a specific status.
     *
     * @param restaurant The restaurant associated with the dining reviews.
     * @param status The status of the dining reviews to find (e.g., APPROVED).
     * @return A list of dining reviews for the given restaurant with the specified status.
     */
    List<DiningReview> findByRestaurantAndStatus(Restaurant restaurant, ReviewStatus status);
}

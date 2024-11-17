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
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DiningReviewRepository diningReviewRepository;

    /**
     * Add a new restaurant, ensuring no duplicate (name + zipcode).
     *
     * @param restaurant The restaurant to add.
     * @return The saved restaurant.
     */
    public Restaurant addRestaurant(Restaurant restaurant) {
        boolean exists = restaurantRepository.existsByNameAndZipcode(
                restaurant.getName(), restaurant.getZipcode());

        if (exists) {
            throw new IllegalArgumentException(
                    "A restaurant with the same name and zip code already exists.");
        }

        return restaurantRepository.save(restaurant);
    }

    /**
     * Get a restaurant by its unique ID.
     *
     * @param restaurantId The ID of the restaurant to retrieve.
     * @return The restaurant entity.
     */
    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
    }

    /**
     * Recompute the scores for a restaurant based on approved dining reviews.
     *
     * @param restaurantId The ID of the restaurant to update.
     */
    public void recomputeRestaurantScores(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        List<DiningReview> approvedReviews = diningReviewRepository.findByRestaurantAndStatus(
                restaurant, ReviewStatus.APPROVED);

        double peanutScoreSum = 0.0, eggScoreSum = 0.0, dairyScoreSum = 0.0;
        int peanutCount = 0, eggCount = 0, dairyCount = 0;

        for (DiningReview review : approvedReviews) {
            if (review.getPeanutScore() != null) {
                peanutScoreSum += review.getPeanutScore();
                peanutCount++;
            }
            if (review.getEggScore() != null) {
                eggScoreSum += review.getEggScore();
                eggCount++;
            }
            if (review.getDairyScore() != null) {
                dairyScoreSum += review.getDairyScore();
                dairyCount++;
            }
        }

        restaurant.setPeanutScore(peanutCount > 0 ? peanutScoreSum / peanutCount : null);
        restaurant.setEggScore(eggCount > 0 ? eggScoreSum / eggCount : null);
        restaurant.setDairyScore(dairyCount > 0 ? dairyScoreSum / dairyCount : null);

        int totalReviews = peanutCount + eggCount + dairyCount;
        double totalScoreSum = peanutScoreSum + eggScoreSum + dairyScoreSum;
        restaurant.setOverallScore(totalReviews > 0 ? totalScoreSum / totalReviews : null);

        restaurantRepository.save(restaurant);
    }

    /**
     * Search for restaurants by zip code and allergy score.
     *
     * @param zipcode The zip code to filter by.
     * @param allergy The allergy type to filter by (e.g., "peanut", "egg", "dairy").
     * @return A list of restaurants matching the criteria, sorted in descending order of the allergy score.
     */
    public List<Restaurant> searchRestaurantsByZipAndAllergy(String zipcode, String allergy) {
        switch (allergy.toLowerCase()) {
            case "peanut":
                return restaurantRepository.findByZipcodeAndPeanutScoreNotNullOrderByPeanutScoreDesc(zipcode);
            case "egg":
                return restaurantRepository.findByZipcodeAndEggScoreNotNullOrderByEggScoreDesc(zipcode);
            case "dairy":
                return restaurantRepository.findByZipcodeAndDairyScoreNotNullOrderByDairyScoreDesc(zipcode);
            default:
                throw new IllegalArgumentException("Invalid allergy type. Use 'peanut', 'egg', or 'dairy'.");
        }
    }
}

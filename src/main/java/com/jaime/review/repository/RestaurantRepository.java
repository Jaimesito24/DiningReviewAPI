package com.jaime.review.repository;

import com.jaime.review.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Check if a restaurant with the same name and zip code exists.
     *
     * @param name    The name of the restaurant.
     * @param zipcode The zip code of the restaurant.
     * @return True if a matching restaurant exists, false otherwise.
     */
    boolean existsByNameAndZipcode(String name, String zipcode);

    /**
     * Retrieve a restaurant by its unique ID (already available via JpaRepository).
     *
     * @param id The ID of the restaurant.
     * @return An Optional containing the restaurant if found.
     */
    Optional<Restaurant> findById(Long id);

    /**
     * Find restaurants by zip code with a non-null peanut score, sorted in descending order.
     *
     * @param zipcode The zip code to filter by.
     * @return A list of restaurants matching the criteria.
     */
    @Query("SELECT r FROM Restaurant r WHERE r.zipcode = :zipcode AND r.peanutScore IS NOT NULL ORDER BY r.peanutScore DESC")
    List<Restaurant> findByZipcodeAndPeanutScoreNotNullOrderByPeanutScoreDesc(@Param("zipcode") String zipcode);

    /**
     * Find restaurants by zip code with a non-null egg score, sorted in descending order.
     *
     * @param zipcode The zip code to filter by.
     * @return A list of restaurants matching the criteria.
     */
    @Query("SELECT r FROM Restaurant r WHERE r.zipcode = :zipcode AND r.eggScore IS NOT NULL ORDER BY r.eggScore DESC")
    List<Restaurant> findByZipcodeAndEggScoreNotNullOrderByEggScoreDesc(@Param("zipcode") String zipcode);

    /**
     * Find restaurants by zip code with a non-null dairy score, sorted in descending order.
     *
     * @param zipcode The zip code to filter by.
     * @return A list of restaurants matching the criteria.
     */
    @Query("SELECT r FROM Restaurant r WHERE r.zipcode = :zipcode AND r.dairyScore IS NOT NULL ORDER BY r.dairyScore DESC")
    List<Restaurant> findByZipcodeAndDairyScoreNotNullOrderByDairyScoreDesc(@Param("zipcode") String zipcode);
}

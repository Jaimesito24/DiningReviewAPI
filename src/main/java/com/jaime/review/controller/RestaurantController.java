package com.jaime.review.controller;

import com.jaime.review.model.Restaurant;
import com.jaime.review.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Add a new restaurant
    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    // Fetch a restaurant by its unique ID
    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    // Search for restaurants by zip code and allergy score
    @GetMapping("/search")
    public List<Restaurant> searchRestaurantsByZipAndAllergy(
            @RequestParam String zipcode,
            @RequestParam String allergy) {
        return restaurantService.searchRestaurantsByZipAndAllergy(zipcode, allergy);
    }
}

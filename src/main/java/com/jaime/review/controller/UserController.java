package com.jaime.review.controller;

import com.jaime.review.model.User;
import com.jaime.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user profile
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Manual validation for creating a user
        if (user == null || user.getDisplayName() == null || user.getDisplayName().isEmpty()) {
            throw new IllegalArgumentException("User display name is required.");
        }

        // Passes the task to the service layer
        return userService.createUser(user);
    }

    // Update an existing user profile
    @PutMapping("/{displayName}")
    public User updateUserProfile(
            @PathVariable String displayName,
            @RequestBody User updatedUserInfo) {
        // Manual validation for updating a user
        if (updatedUserInfo == null || displayName == null || displayName.isEmpty()) {
            throw new IllegalArgumentException("Display name and updated user information are required.");
        }

        // Passes the task to the service layer
        return userService.updateUserProfile(displayName, updatedUserInfo);
    }

    // Fetch a user profile by display name
    @GetMapping("/{displayName}")
    public User getUserProfile(@PathVariable String displayName) {
        // Manual validation for display name
        if (displayName == null || displayName.isEmpty()) {
            throw new IllegalArgumentException("Display name is required.");
        }

        // Passes the task to the service layer
        return userService.getUserByDisplayName(displayName);
    }
}

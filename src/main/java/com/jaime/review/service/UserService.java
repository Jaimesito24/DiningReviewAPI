package com.jaime.review.service;

import com.jaime.review.model.User;
import com.jaime.review.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsByDisplayName(user.getDisplayName())) {
            throw new IllegalArgumentException("Display name already taken.");
        }
        return userRepository.save(user);
    }

    public User updateUserProfile(String displayName, User updatedUserInfo) {
        User user = userRepository.findByDisplayName(displayName)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // Only update allowed fields
        user.setCity(updatedUserInfo.getCity());
        user.setState(updatedUserInfo.getState());
        user.setZipcode(updatedUserInfo.getZipcode());
        user.setInterestedInPeanutAllergy(updatedUserInfo.getInterestedInPeanutAllergy());
        user.setInterestedInEggAllergy(updatedUserInfo.getInterestedInEggAllergy());
        user.setInterestedInDairyAllergy(updatedUserInfo.getInterestedInDairyAllergy());
        return userRepository.save(user);
    }

    public User getUserByDisplayName(String displayName) {
        return userRepository.findByDisplayName(displayName)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

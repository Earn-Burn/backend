package com.example.client.services;


import com.example.client.models.User;
import com.example.client.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User useOffer(String userId, String offerId, Integer pointsToDeduct) {
        if (userId == null || offerId == null || pointsToDeduct == null) {
            throw new IllegalArgumentException("userId, offerId, and pointsToDeduct must not be null");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getPoints() >= pointsToDeduct) {
            user.setPoints(user.getPoints() - pointsToDeduct);
            user.getUsedOfferIds().add(offerId);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Not enough points");
        }

        return user;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

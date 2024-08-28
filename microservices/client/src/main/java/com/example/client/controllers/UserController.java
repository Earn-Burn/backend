package com.example.client.controllers;

import com.example.client.models.User;
import com.example.client.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{userId}/use-offer")
    public ResponseEntity<User> useOffer(@PathVariable String userId, @RequestBody Map<String, Object> payload) {
        String offerId = (String) payload.get("offerId");
        Integer pointsToDeduct = (Integer) payload.get("pointsToDeduct");

        if (offerId == null || pointsToDeduct == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            User updatedUser = userService.useOffer(userId, offerId, pointsToDeduct);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
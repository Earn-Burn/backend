package com.example.offers.services;


import com.example.client.services.UserService;
import com.example.offers.models.Offer;
import com.example.client.models.User;
import com.example.offers.repositories.OfferRepository;
import com.example.client.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository; // Ajouter cette ligne

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public Optional<Offer> getOfferById(String id) {
        return offerRepository.findById(id);
    }

    public List<Offer> getOffersUsedByUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<String> usedOfferIds = user.get().getUsedOfferIds();  // Récupérer la liste des offres utilisées
            System.out.println("User ID: " + userId);
            System.out.println("Used Offer IDs: " + usedOfferIds);
            return offerRepository.findByIdIn(usedOfferIds);  // Rechercher les offres par leurs ID
        } else {
            return Collections.emptyList();
        }
    }
}

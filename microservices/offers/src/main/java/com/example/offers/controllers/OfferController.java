package com.example.offers.controllers;

import com.example.offers.models.Offer;
import com.example.offers.services.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/offers")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }


    @PostMapping("/save")
    public ResponseEntity<Offer> createOffer(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("requiredPoints") Integer requiredPoints,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        String imageBase64 = null;
        // Vérifie si une image a été uploadée
        if (image != null && !image.isEmpty()) {
            // Encode l'image en Base64
            imageBase64 = encodeImageToBase64(image);
        }

        // Crée une nouvelle instance de l'entité Offer
        Offer offer = new Offer();
        offer.setTitle(title);
        offer.setDescription(description);
        offer.setRequiredPoints(requiredPoints);
        offer.setImageBase64(imageBase64);

        // Sauvegarde l'offre en utilisant le service correspondant
        Offer savedOffer = offerService.saveOffer(offer);

        // Retourne une réponse HTTP avec l'offre sauvegardée
        return ResponseEntity.ok(savedOffer);
    }

    // Méthode pour encoder une image en Base64
    private String encodeImageToBase64(MultipartFile image) throws IOException {
        // Convertit l'image en tableau d'octets
        byte[] bytes = image.getBytes();
        // Encode les octets en chaîne Base64
        return Base64.getEncoder().encodeToString(bytes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable String id) {
        Optional<Offer> offer = offerService.getOfferById(id);
        return offer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Offer>> getOffersUsedByUser(@PathVariable String userId) {
        List<Offer> offers = offerService.getOffersUsedByUser(userId);
        return ResponseEntity.ok(offers);
    }


}

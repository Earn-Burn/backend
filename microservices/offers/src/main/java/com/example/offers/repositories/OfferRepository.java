package com.example.offers.repositories;


import com.example.offers.models.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {
    List<Offer> findByIdIn(List<String> ids);
}

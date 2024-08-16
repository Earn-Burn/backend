package com.fide.events.repositories;

import com.fide.events.models.Product;
import com.fide.events.models.Typologie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    public List<Product> findByDomain (Typologie domain);
}


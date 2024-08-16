package com.fide.events.services;

import com.fide.events.models.Product;
import com.fide.events.models.Typologie;
import com.fide.events.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findByDomain (Typologie domain) {
        return productRepository.findByDomain(domain);
    }

    public List<Typologie> findAllDomains() {
        return Arrays.asList(Typologie.values());
    }
}

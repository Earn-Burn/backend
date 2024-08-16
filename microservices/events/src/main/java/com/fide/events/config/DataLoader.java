package com.fide.events.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fide.events.models.Product;
import com.fide.events.models.Typologie;
import com.fide.events.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load and process TYPGROUP.json
        Resource groupResource = resourceLoader.getResource("classpath:TYPGROUP.json");
        InputStream groupInputStream = groupResource.getInputStream();
        List<Product> groupProducts = objectMapper.readValue(groupInputStream, new TypeReference<List<Product>>() {});
        groupProducts.forEach(entity -> entity.setDomain(Typologie.GROUP)); // Set domain to GROUP

        // Load and process TYPFAMILY.json
        Resource familyResource = resourceLoader.getResource("classpath:TYPFAMILY.json");
        InputStream familyInputStream = familyResource.getInputStream();
        List<Product> familyProducts = objectMapper.readValue(familyInputStream, new TypeReference<List<Product>>() {});
        familyProducts.forEach(entity -> entity.setDomain(Typologie.FAMILY)); // Set domain to FAMILY

        // Save all entities to the database
        repository.saveAll(groupProducts);
        repository.saveAll(familyProducts);
    }

    @PreDestroy
    public void cleanUp() {
        repository.deleteAll();
    }
}
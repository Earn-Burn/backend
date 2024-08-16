package com.fide.events.controllers;

import com.fide.events.models.Product;
import com.fide.events.models.Typologie;
import com.fide.events.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("product/{domain}")
    public List<Product> getProductByDomain(@PathVariable Typologie domain) {
        return productService.findByDomain(domain);
    }
}

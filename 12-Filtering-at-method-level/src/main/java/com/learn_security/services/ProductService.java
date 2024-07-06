package com.learn_security.services;

import com.learn_security.models.Product;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @PreFilter(value = "filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        return products;
    }
}

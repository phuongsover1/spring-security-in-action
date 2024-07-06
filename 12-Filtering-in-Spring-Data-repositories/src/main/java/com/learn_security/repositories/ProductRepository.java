package com.learn_security.repositories;

import com.learn_security.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    @PostFilter("filterObject.owner == authentication.name")

    @Query("""
                    SELECT p FROM Product p WHERE 
                    p.name LIKE %:text% AND 
                    p.owner =?#{authentication.name}
            """)
    List<Product> findProductByNameContains(String text);
}

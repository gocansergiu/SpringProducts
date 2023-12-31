package com.products.springProducts.repository;

import com.products.springProducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @SuppressWarnings("unused")
    Optional<Product> findByName(String name);
}

package com.example.HelaCane.Repository;

import com.example.HelaCane.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity ,Long> {
}

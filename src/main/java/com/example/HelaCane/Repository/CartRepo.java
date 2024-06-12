package com.example.HelaCane.Repository;

import com.example.HelaCane.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<CartEntity ,Long> {
}

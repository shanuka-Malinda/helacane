package com.example.HelaCane.Repository;

import com.example.HelaCane.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String username);
}

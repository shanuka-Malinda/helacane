package com.example.HelaCane.Repository;

import com.example.HelaCane.Entity.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepo extends JpaRepository<RepairEntity,Long> {
}

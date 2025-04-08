package com.example.healthcare.Repository;

import com.example.healthcare.Entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    Optional<Medicine> findByMedicineName(String medicineName);
    List<Medicine> findByUserId(Long userId);
}

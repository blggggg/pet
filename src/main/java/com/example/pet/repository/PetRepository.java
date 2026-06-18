package com.example.pet.repository;

import com.example.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByNameContainingIgnoreCaseOrTypeContainingIgnoreCaseOrOwnerNameContainingIgnoreCase(
            String name, String type, String ownerName);
}

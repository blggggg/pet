package com.example.pet.service;


import com.example.pet.entity.Pet;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PetService {
    List<Pet> list();
    Pet getById(Long id);
    void save(Pet pet);
    void update(Pet pet);

    String generateReport(Long id, Pet  pet, HttpServletResponse response);
}

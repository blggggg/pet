package com.example.pet.service;

import com.example.pet.entity.User;

public interface UserService {
    User login(String username, String password);
}

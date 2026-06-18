package com.example.pet.service.impl;

import com.example.pet.entity.User;
import com.example.pet.repository.UserRepository;
import com.example.pet.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new IllegalArgumentException("用户名和密码不能为空");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("用户名不存在"));

        if (user.getEnabled() == null || user.getEnabled() != 1) {
            throw new IllegalArgumentException("账号已禁用");
        }

        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("密码错误");
        }

        return user;
    }
}

package ru.project.viviv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.entity.User;
import ru.project.viviv.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUserById(UUID id){
        return userRepository.findById(id).orElse(null);
    }
}
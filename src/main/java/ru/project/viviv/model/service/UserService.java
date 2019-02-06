package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.repository.UserRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(@NotNull User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void removeUser(@NotNull User user) {
        userRepository.delete(user);
    }

    public void removeUserById(@NotNull String id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllByEmail(@NotNull String email) {
        return userRepository.findAllByEmail(email);
    }

    public User getUserById(@NotNull String id) {
        return userRepository.findById(id).orElse(null);
    }
}
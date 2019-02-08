package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.dto.UserDTO;
import ru.project.viviv.model.entity.Role;
import ru.project.viviv.model.entity.RoleConnection;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.repository.RoleRepository;
import ru.project.viviv.model.repository.UserRepository;
import ru.project.viviv.validation.EmailExistsException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public User findByEmail(@NotNull String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(@NotNull String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException("Пользователь с таким email уже существует: " + accountDto.getEmail());
        }
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        RoleConnection roleConnection = new RoleConnection();
        Role role = roleRepository.findByRole("USER");
        roleConnection.setRole(role);
        user.getRoleConnections().add(roleConnection);
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }


}
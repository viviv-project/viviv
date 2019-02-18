package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.dto.RoleDTO;
import ru.project.viviv.model.dto.UserDTO;
import ru.project.viviv.model.entity.*;
import ru.project.viviv.model.repository.FriendRepository;
import ru.project.viviv.model.repository.RoleRepository;
import ru.project.viviv.model.repository.UserRepository;
import ru.project.viviv.model.repository.VerificationTokenRepository;
import ru.project.viviv.validation.EmailExistsException;
import ru.project.viviv.validation.UsernameExistsException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private FriendService friendService;

    public void saveUser(@NotNull User user) {
        userRepository.save(user);
    }

    public User saveAndReturnUser(@NotNull User user) {
        return userRepository.save(user);
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

    public User findByUsername(@NotNull String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User registerNewUserAccount(@NotNull UserDTO accountDto) throws EmailExistsException, UsernameExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException("Пользователь с таким email уже существует: " + accountDto.getEmail());
        }
        if (usernameExists(accountDto.getUsername())) {
            throw new UsernameExistsException("Пользователь с таким никнеймом уже сущестует: " + accountDto.getUsername());
        }

        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        RoleConnection roleConnection = new RoleConnection();
        Role role = roleRepository.findByStatus(RoleStatus.USER);
        roleConnection.setRole(role);
        user.getRoleConnections().add(roleConnection);
        return saveAndReturnUser(user);
    }

    private boolean emailExists(@NotNull String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private boolean usernameExists(@NotNull String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public User getUser(@NotNull String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

    public VerificationToken getVerificationToken(@NotNull String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    public void saveRegisteredUser(@NotNull User user) {
        userRepository.save(user);
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Transactional
    public User updateRoles(@NotNull RoleDTO roleDTO, @NotNull User user) {
        user.getRoleConnections().clear();
        if (roleDTO.getIsAdmin() != null && roleDTO.getIsAdmin()) {
            user.getRoleConnections().add(new RoleConnection(roleRepository.findByStatus(RoleStatus.ADMIN)));
        }
        user.getRoleConnections().add(new RoleConnection(roleRepository.findByStatus(RoleStatus.USER)));
        return user;
    }

    //todo заменить ACCEPT на REQUEST
    public void addFriend(@NotNull String username, @NotNull String friendUsername){
        FriendSource friendSource = new FriendSource();
        friendSource.setStatus(FriendStatus.ACCEPT);
        friendSource.setUser(findByUsername(username));
        FriendTarget friendTarget = new FriendTarget();
        friendTarget.setStatus(FriendStatus.ACCEPT);
        friendTarget.setUser(findByUsername(friendUsername));
        Friend friend = new Friend(friendSource, friendTarget);
        friendService.saveFriend(friend);
    }
}
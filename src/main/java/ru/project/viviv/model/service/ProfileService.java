package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.dto.UserDTO;
import ru.project.viviv.model.entity.Profile;
import ru.project.viviv.model.entity.Role;
import ru.project.viviv.model.entity.RoleConnection;
import ru.project.viviv.model.entity.RoleStatus;
import ru.project.viviv.model.repository.ProfileRepository;
import ru.project.viviv.model.repository.RoleRepository;
import ru.project.viviv.validation.EmailExistsException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createProfile(@NotNull Profile profile) {
        profileRepository.save(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public void removeProfile(@NotNull Profile profile) {
        profileRepository.delete(profile);
    }

    public void removeProfileById(@NotNull String id) {
        profileRepository.deleteById(id);
    }

    public List<Profile> findAllByFirstname(@NotNull String firstname) {
        return profileRepository.findAllByFirstname(firstname);
    }

    public Profile findByEmail(@NotNull String email){
        return profileRepository.findByEmail(email);
    }

    public List<Profile> findAllSortedByDate() {
        return profileRepository.findAllByOrderByDateAddedDesc();
    }

    @Transactional
    public Profile registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException("Пользователь с таким email уже существует: " + accountDto.getEmail());
        }
        Profile profile = new Profile();
        profile.setUsername(accountDto.getUsername());
        profile.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        profile.setEmail(accountDto.getEmail());
        RoleConnection roleConnection = new RoleConnection();
        Role role = roleRepository.findByStatus(RoleStatus.USER);
        roleConnection.setRole(role);
        profile.getRoleConnections().add(roleConnection);
        return profileRepository.save(profile);
    }

    private boolean emailExists(String email) {
        Profile user = profileRepository.findByEmail(email);
        return user != null;
    }
}
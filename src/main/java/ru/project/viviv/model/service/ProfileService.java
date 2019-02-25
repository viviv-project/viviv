package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.Profile;
import ru.project.viviv.model.repository.ProfileRepository;
import ru.project.viviv.model.repository.RoleRepository;

import javax.persistence.EntityManager;
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

    public void createProfile(@NotNull Profile profile) {
        profileRepository.save(profile);
    }

    public void saveProfile(@NotNull Profile profile) {
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

    public List<Profile> findAllSortedByDate() {
        return profileRepository.findAllByOrderByDateAddedDesc();
    }
}
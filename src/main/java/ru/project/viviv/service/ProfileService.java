package ru.project.viviv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.entity.Profile;
import ru.project.viviv.repository.ProfileRepository;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    public void createProfile(Profile profile){
        profileRepository.save(profile);
    }

    public List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    public void removeProfile(Profile profile){
        profileRepository.delete(profile);
    }

    public void removeProfileById(String id){
        profileRepository.deleteById(id);
    }
}

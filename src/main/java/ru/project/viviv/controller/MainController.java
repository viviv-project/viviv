package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.project.viviv.entity.Interest;
import ru.project.viviv.entity.Profile;
import ru.project.viviv.service.InterestService;
import ru.project.viviv.service.ProfileService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    ProfileService profileService;
    @Autowired
    InterestService interestService;

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping(value = "/createProfile", method = RequestMethod.POST)
    public void createProfile(){
        Profile profile = new Profile();
        profile.setFirstname("test");
        profile.setDate_added(LocalDateTime.now());

        Interest interest = new Interest();
        interest.setInterest("спорт");
        Interest interest1 = new Interest();
        interest1.setInterest("чтение");

        profile.getInterests().add(interest);
        interest.getProfiles().add(profile);
        profile.getInterests().add(interest1);
        interest1.getProfiles().add(profile);

        profileService.createProfile(profile);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Profile> getAllProfiles(){
        List<Profile> profiles = profileService.getAllProfiles();
        return profileService.getAllProfiles();
    }
}
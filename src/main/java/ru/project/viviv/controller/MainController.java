package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.project.viviv.model.entity.*;
import ru.project.viviv.model.service.InterestService;
import ru.project.viviv.model.service.ProfileService;

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
        profile.setDateAdded(LocalDateTime.now());

        Interest interest = new Interest();
        interest.setInterest("спорт");
        Interest interest1 = new Interest();
        interest1.setInterest("чтение");
        InterestConnection interestConnection = new InterestConnection();
        interestConnection.setInterest(interest);
        interestService.saveInterest(interest);
        interestService.saveInterest(interest1);
        interestConnection.setProfile(profile);
        interest.getInterestConnections().add(interestConnection);
        profile.getInterestConnections().add(interestConnection);
        profileService.createProfile(profile);
        System.out.println("done");
    }

    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public List<Profile> friends(){
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        profile1.setFirstname("Source friend");
        profile2.setFirstname("Target friend");
        profileService.createProfile(profile1);
        profileService.createProfile(profile2);

        FriendSource friendSource = new FriendSource();
        friendSource.setProfile(profile1);
        FriendTarget friendTarget = new FriendTarget();
        friendTarget.setFriendSource(friendSource);
        friendTarget.setProfile(profile2);
        friendSource.getTargetFriends().add(friendTarget);
        profile1.getSourceFriends().add(friendSource);
        profileService.createProfile(profile1);
        profile2.getFriendTargets().add(friendTarget);
        profileService.createProfile(profile2);
        return getAllProfiles();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Profile> getAllProfiles(){
        List<Profile> profiles = profileService.getAllProfiles();
        return profileService.getAllProfiles();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeProfileById(){
        profileService.removeProfileById("ee864eca-bdde-45af-9ae2-d214489abb7a");
    }

    @RequestMapping(value = "/interest/{interest}", method = RequestMethod.POST)
    public void createInterest(@PathVariable("interest") String strInterest){
        Interest interest = new Interest();
        interest.setInterest(strInterest);
        interestService.saveInterest(interest);
    }
}
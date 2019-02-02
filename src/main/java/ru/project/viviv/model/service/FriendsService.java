//package ru.project.viviv.model.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.project.viviv.model.entity.Friends;
//import ru.project.viviv.model.repository.FriendsRepository;
//
//import java.util.UUID;
//
//@Service
//public class FriendsService {
//    @Autowired
//    FriendsRepository friendsRepository;
//
//    public void saveFriends(Friends friends){
//        friendsRepository.save(friends);
//    }
//
//    public Friends getFriendsById(UUID id){
//        return friendsRepository.findById(id).orElse(null);
//    }
//}
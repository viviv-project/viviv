//package ru.project.viviv.model.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.project.viviv.model.entity.UserRoles;
//import ru.project.viviv.model.repository.UserRolesRepository;
//
//import java.util.UUID;
//
//@Service
//public class UserRolesService {
//    @Autowired
//    UserRolesRepository userRolesRepository;
//
//    public void saveUserRoles(UserRoles userRoles){
//        userRolesRepository.save(userRoles);
//    }
//
//    public UserRoles getUserRolesById(UUID id){
//        return userRolesRepository.findById(id).orElse(null);
//    }
//}
package ru.project.viviv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.entity.Role;
import ru.project.viviv.repository.RoleRepository;

import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public void saveRole(Role role){
        roleRepository.save(role);
    }

    public Role getRoleById(UUID id){
        return roleRepository.findById(id).orElse(null);
    }
}
package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.Role;
import ru.project.viviv.model.repository.RoleRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public void createRole(@NotNull Role role) {
        roleRepository.save(role);
    }

    public Role getRoleById(@NotNull String id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role getRoleByName(@NotNull String name) {
        return roleRepository.findByRole(name);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void removeAllRoles() {
        roleRepository.deleteAll();
    }

    public void removeRoleById(@NotNull String id) {
        roleRepository.deleteById(id);
    }

    public void removeRole(@NotNull Role role) {
        roleRepository.delete(role);
    }
}
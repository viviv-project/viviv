package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRole(String name);
}
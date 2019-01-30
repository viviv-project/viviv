package ru.project.viviv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.entity.UserRoles;

import java.util.UUID;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UUID> {
}

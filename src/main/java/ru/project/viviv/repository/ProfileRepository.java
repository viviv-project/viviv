package ru.project.viviv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
}
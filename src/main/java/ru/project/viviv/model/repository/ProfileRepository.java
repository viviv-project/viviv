package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Profile;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

    List<Profile> findAllByFirstname(String firstname);

    List<Profile> findAllByOrderByDateAddedDesc();

    Profile findByEmail(String email);
}
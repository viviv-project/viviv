package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
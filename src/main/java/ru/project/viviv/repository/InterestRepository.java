package ru.project.viviv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.entity.Interest;

import java.util.UUID;

@Repository
public interface InterestRepository extends JpaRepository<Interest, UUID> {
}
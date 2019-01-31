package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Interest;

@Repository
public interface InterestRepository extends JpaRepository<Interest, String> {
}
package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
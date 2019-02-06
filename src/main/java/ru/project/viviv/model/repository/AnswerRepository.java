package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
}
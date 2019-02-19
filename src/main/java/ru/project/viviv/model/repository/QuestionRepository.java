package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Question;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    Optional<Question> findByQuestion(String name);
}
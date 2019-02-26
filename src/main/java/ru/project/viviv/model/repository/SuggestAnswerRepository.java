package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.SuggestAnswer;
import ru.project.viviv.model.entity.UserQuestion;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuggestAnswerRepository extends JpaRepository<SuggestAnswer, String> {
    List<SuggestAnswer> findAllByProfile_IdAndAndUserQuestion(String profileId, UserQuestion userQuestion);
    Optional<SuggestAnswer> findByProfile_IdAndUserQuestion(String profileId, UserQuestion userQuestion);
}

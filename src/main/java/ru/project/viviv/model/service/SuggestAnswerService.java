package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.SuggestAnswer;
import ru.project.viviv.model.entity.UserQuestion;
import ru.project.viviv.model.repository.SuggestAnswerRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuggestAnswerService {
    @Autowired
    private SuggestAnswerRepository suggestAnswerRepository;

    public List<SuggestAnswer> findAllSuggestAnswers(String profileId, List<UserQuestion> userQuestions) {
        return userQuestions.stream().map(userQuestion ->
                suggestAnswerRepository.findByProfile_IdAndUserQuestion(profileId, userQuestion).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void createSuggestAnswer(SuggestAnswer suggestAnswer){
        suggestAnswerRepository.save(suggestAnswer);
    }

    public Boolean isStatusExists(boolean status){
        return suggestAnswerRepository.existsByStatus(status);
    }
}

package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.Answer;
import ru.project.viviv.model.repository.AnswerRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public void createAnswer(@NotNull Answer answer) {
        answerRepository.save(answer);
    }

    public Answer getAnswerById(@NotNull String id) {
        return answerRepository.findById(id).orElse(null);
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public void removeAllAnswers() {
        answerRepository.deleteAll();
    }

    public void removeAnswersById(@NotNull String id) {
        answerRepository.deleteById(id);
    }

    public void removeAnswers(@NotNull Answer answer) {
        answerRepository.delete(answer);
    }

    public Optional<Answer> findAnswerByName(@NotNull String name) {
        return answerRepository.findByAnswer(name);
    }
}
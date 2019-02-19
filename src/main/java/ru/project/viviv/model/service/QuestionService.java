package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.Question;
import ru.project.viviv.model.repository.QuestionRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public void createQuestion(@NotNull Question question) {
        questionRepository.save(question);
    }

    public Question getQuestionById(@NotNull String id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void removeAllQuestions() {
        questionRepository.deleteAll();
    }

    public void removeQuestionsById(@NotNull String id) {
        questionRepository.deleteById(id);
    }

    public void removeQuestions(@NotNull Question question) {
        questionRepository.delete(question);
    }

    public Optional<Question> findQuestionByName(@NotNull String name) {
        return questionRepository.findByQuestion(name);
    }
}
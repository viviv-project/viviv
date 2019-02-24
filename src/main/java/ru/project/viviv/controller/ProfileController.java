package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.project.viviv.model.dto.QuestionDTO;
import ru.project.viviv.model.entity.Answer;
import ru.project.viviv.model.entity.Question;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.entity.UserQuestion;
import ru.project.viviv.model.service.AnswerService;
import ru.project.viviv.model.service.QuestionService;
import ru.project.viviv.model.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @Value("${question.size}")
    private int questionSize;

    @PostMapping(value = "question")
    public ModelAndView addQuestion(@ModelAttribute("question") QuestionDTO questionDTO) {
        User user = userService.findByUsername(questionDTO.getUsername());
        List<UserQuestion> userQuestions = user.getProfile().getUserQuestions();
        if (userQuestions.size() >= questionSize) {
            return new ModelAndView("redirect:/login");
        }
        if (questionDTO.getQuestion() != null && questionDTO.getAnswer() != null
                && !questionDTO.getQuestion().isEmpty() && !questionDTO.getAnswer().isEmpty()) {
            Optional<Question> optionalQuestion = questionService.findQuestionByName(questionDTO.getQuestion());
            Question question = optionalQuestion.orElse(new Question(questionDTO.getQuestion()));
            Optional<Answer> optionalAnswer = answerService.findAnswerByName(questionDTO.getAnswer());
            Answer answer = optionalAnswer.orElse(new Answer(questionDTO.getAnswer()));

            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setAnswer(answer);
            userQuestion.setQuestion(question);

            user.getProfile().getUserQuestions().add(userQuestion);
            userService.saveUser(user);
        }
        if (userQuestions.size() == questionSize) {
            return new ModelAndView("redirect:/login");
        }
        questionDTO.setFilledCount(userQuestions.size() + 1);
        questionDTO.setAnswer(null);
        questionDTO.setQuestion(null);
        return new ModelAndView("question", "question", questionDTO);
    }
}

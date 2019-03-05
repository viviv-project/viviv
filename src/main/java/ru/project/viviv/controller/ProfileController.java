package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.project.viviv.common.Converter;
import ru.project.viviv.model.dto.ProfileDTO;
import ru.project.viviv.model.dto.QuestionFillDTO;
import ru.project.viviv.model.entity.*;
import ru.project.viviv.model.service.AnswerService;
import ru.project.viviv.model.service.ProfileService;
import ru.project.viviv.model.service.QuestionService;
import ru.project.viviv.model.service.UserService;

import java.security.Principal;
import java.time.LocalDate;
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
    @Autowired
    private Converter converter;
    @Autowired
    private ProfileService profileService;

    @Value("${question.size}")
    private int questionSize;

    @PostMapping(value = "question")
    public ModelAndView addQuestion(@ModelAttribute("question") QuestionFillDTO questionFillDto) {
        User user = userService.findByUsername(questionFillDto.getUsername());
        List<UserQuestion> userQuestions = user.getProfile().getUserQuestions();
        if (userQuestions.size() >= questionSize) {
            return new ModelAndView("redirect:/index");
        }
        if (questionFillDto.getQuestion() != null && questionFillDto.getAnswer() != null
                && !questionFillDto.getQuestion().isEmpty() && !questionFillDto.getAnswer().isEmpty()) {
            Optional<Question> optionalQuestion = questionService.findQuestionByName(questionFillDto.getQuestion());
            Question question = optionalQuestion.orElse(new Question(questionFillDto.getQuestion()));
            Optional<Answer> optionalAnswer = answerService.findAnswerByName(questionFillDto.getAnswer());
            Answer answer = optionalAnswer.orElse(new Answer(questionFillDto.getAnswer()));

            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setAnswer(answer);
            userQuestion.setQuestion(question);

            user.getProfile().getUserQuestions().add(userQuestion);
            userService.saveUser(user);
        }
        if (userQuestions.size() == questionSize) {
            return new ModelAndView("redirect:/index");
        }
        questionFillDto.setFilledCount(userQuestions.size() + 1);
        questionFillDto.setAnswer(null);
        questionFillDto.setQuestion(null);
        return new ModelAndView("question", "question", questionFillDto);
    }

    @GetMapping(value = "question")
    public ModelAndView fillingQuestions(Principal principal) {
        QuestionFillDTO questionFillDto = new QuestionFillDTO();
        questionFillDto.setUsername(principal.getName());
        return addQuestion(questionFillDto);
    }

    @GetMapping(value = "editProfile")
    public ModelAndView editProfile(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ProfileDTO profileDto = converter.profileToDto(user.getProfile());
        return new ModelAndView("profile-edit", "profileDto", profileDto);
    }

    @PostMapping(value = "editProfile")
    public ModelAndView updateProfile(@ModelAttribute(value = "profileDto") ProfileDTO profileDto, Principal principal) {
        Profile profile = userService.findByUsername(principal.getName()).getProfile();
        profile.setBirthDate(LocalDate.parse(profileDto.getBirthDate()));
        profile.setFirstname(profileDto.getFirstname());
        profile.setLastname(profileDto.getLastname());
        profile.setSex(profileDto.getCharSex() == null ? null : profileDto.getCharSex() == 'М' ? 0 : 1);
        profile.setPhone(profileDto.getPhone());
        profileService.saveProfile(profile);
        return new ModelAndView("redirect:/" + principal.getName());
    }

}

package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.project.viviv.common.Converter;
import ru.project.viviv.model.dto.*;
import ru.project.viviv.model.entity.*;
import ru.project.viviv.model.service.AnswerService;
import ru.project.viviv.model.service.FriendService;
import ru.project.viviv.model.service.SuggestAnswerService;
import ru.project.viviv.model.service.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private Converter converter;
    @Autowired
    private SuggestAnswerService suggestAnswerService;
    @Autowired
    private AnswerService answerService;
    @Value("${question.size}")
    private int questionSize;

    @GetMapping("{username}")
    public ModelAndView user(@PathVariable(name = "username") String username, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ProfileQuestionDTO profileQuestionDTO = converter.profileQuestionToDto(user);
        if (username.equals(principal.getName())) {
            return new ModelAndView("profile", "profileQuestion", profileQuestionDTO);
        }
        User target = userService.findByUsername(username);
        if (isForbidden(username, principal)) {
            List<UserQuestion> targetQuestions = target.getProfile().getUserQuestions();
            List<SuggestAnswer> userSuggestAnswers = suggestAnswerService.findAllSuggestAnswers(user.getId(), targetQuestions);
            Map<String, AnswerSuggestDTO> existsAnswerSuggestsDto = new HashMap<>();
            if (userSuggestAnswers != null) {
                existsAnswerSuggestsDto = userSuggestAnswers.stream()
                        .map(suggestAnswer -> converter.suggestAnswerToDto(suggestAnswer, user, target))
                        .collect(Collectors.toMap(AnswerSuggestDTO::getQuestion, a -> a));
            }

            Map<String, AnswerSuggestDTO> finalExistsAnswerSuggestsDto = existsAnswerSuggestsDto;
            AtomicInteger counter = new AtomicInteger(0);
            List<AnswerSuggestDTO> answerSuggestsDTO = targetQuestions.stream()
                    .map(targetQuestion -> {
                        if (finalExistsAnswerSuggestsDto.keySet().contains(targetQuestion.getQuestion().getQuestion())) {
                            counter.incrementAndGet();
                            return finalExistsAnswerSuggestsDto.get(targetQuestion.getQuestion().getQuestion());
                        } else {
                            return new AnswerSuggestDTO(targetQuestion.getQuestion().getQuestion(), target.getUsername(), user.getUsername());
                        }
                    })
                    .collect(Collectors.toList());
            if (counter.get() == questionSize) {
                int rightCount = (int) answerSuggestsDTO.stream().filter(answer -> answer.getStatus().equals(true)).count();
                if (questionSize == rightCount) {
                    userService.addFriend(user.getUsername(), target.getUsername());
                    answerSuggestsDTO.add(new AnswerSuggestDTO(true, target.getUsername()));
                }
            }
            return new ModelAndView("questionnaire", "answerSuggests", answerSuggestsDTO);
        }
        ProfileDTO profileDto = converter.profileToDto(target.getProfile());
        ProfileViewDTO profileViewDto = new ProfileViewDTO();
        profileViewDto.setProfileDto(profileDto);
        profileViewDto.setUsername(target.getUsername());
        return new ModelAndView("profile-view", "profileView", profileViewDto);
    }

    @PostMapping("questionnaire")
    public ModelAndView questionnaire(@ModelAttribute("answerSuggest") AnswerSuggestDTO answerSuggestDto) {
        Profile targetProfile = userService.findByUsername(answerSuggestDto.getQuestionAuthor()).getProfile();
        Profile userProfile = userService.findByUsername(answerSuggestDto.getUsername()).getProfile();
        if (suggestAnswerService.isStatusExists(true) || suggestAnswerService.isStatusExists(false)) return new ModelAndView("redirect:/");

        targetProfile.getUserQuestions()
                .forEach(targetQuestion -> {
                    if (targetQuestion.getQuestion().getQuestion().equals(answerSuggestDto.getQuestion())
                            && targetQuestion.getAnswer().getAnswer().equals(answerSuggestDto.getAnswerSuggest())) {
                        SuggestAnswer suggestAnswerTrue = new SuggestAnswer(targetQuestion.getAnswer(), userProfile, targetQuestion, true);
                        suggestAnswerService.createSuggestAnswer(suggestAnswerTrue);
                    } else if (targetQuestion.getQuestion().getQuestion().equals(answerSuggestDto.getQuestion())) {
                        SuggestAnswer suggestAnswerFalse = new SuggestAnswer(answerService.findAnswerByName(
                                answerSuggestDto.getAnswerSuggest()).orElse(
                                new Answer(answerSuggestDto.getAnswerSuggest())), userProfile, targetQuestion, false);
                        suggestAnswerService.createSuggestAnswer(suggestAnswerFalse);
                    }
                });
        return new ModelAndView("redirect:/" + answerSuggestDto.getQuestionAuthor());
    }

    @GetMapping("friends")
    public ModelAndView friends(Principal principal) {
        List<User> friends = friendService.findAllUserFriends(userService.findByUsername(principal.getName()));
        List<UserProfileDTO> friendsDto = friends.stream().map(friend -> new UserProfileDTO(converter.userToDto(friend), converter.profileToDto(friend.getProfile()))).collect(Collectors.toList());
        return new ModelAndView("friends", "friends", friendsDto);
    }

    @GetMapping("allUsers")
    public ModelAndView allUsers(Principal principal) {
        List<User> users = userService.getAllUsers();
        users.remove(userService.findByUsername(principal.getName()));
        users.removeAll(friendService.findAllUserRelations(userService.findByUsername(principal.getName())));
        users = users.stream().filter(User::getEnabled).filter(user -> user.getProfile().getUserQuestions().size() == questionSize).collect(Collectors.toList());
        List<UserProfileDTO> usersDto = users.stream().map(user -> new UserProfileDTO(converter.userToDto(user), converter.profileToDto(user.getProfile()))).collect(Collectors.toList());
        return new ModelAndView("all-users", "users", usersDto);
    }

    @PostMapping("addFriend")
    public RedirectView addFriend(@RequestParam String friendUsername, Principal principal) {
        userService.addFriend(principal.getName(), friendUsername);
        return new RedirectView("allUsers");
    }

    private boolean isForbidden(String username, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (user.getUsername().equals(username)) return false;
        User potentialFriend = userService.findByUsername(username);
        return !friendService.findAllUserFriends(user).contains(potentialFriend);
    }
}
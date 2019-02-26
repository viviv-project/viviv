package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.project.viviv.common.Converter;
import ru.project.viviv.model.dto.AnswerSuggestDTO;
import ru.project.viviv.model.dto.FullInfoDTO;
import ru.project.viviv.model.dto.ProfileDTO;
import ru.project.viviv.model.entity.SuggestAnswer;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.entity.UserQuestion;
import ru.project.viviv.model.service.FriendService;
import ru.project.viviv.model.service.SuggestAnswerService;
import ru.project.viviv.model.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
    @Value("${question.size}")
    private int questionSize;

    @GetMapping("{username}")
    public ModelAndView user(@PathVariable(name = "username") String username, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (isForbidden(username, principal)) {
            User target = userService.findByUsername(username);
            List<UserQuestion> targetQuestions = target.getProfile().getUserQuestions();
            List<SuggestAnswer> userSuggestAnswers = suggestAnswerService.findAllSuggestAnswers(user.getId(), targetQuestions);
            List<String> filledQuestions = new ArrayList<>();
            if (!userSuggestAnswers.isEmpty()) {
                filledQuestions = userSuggestAnswers.stream().map(a -> a.getUserQuestion().getQuestion().getQuestion()).collect(Collectors.toList());
            }
            List<String> finalFilledQuestions = filledQuestions;
            List<AnswerSuggestDTO> answerSuggestsDTO = targetQuestions.stream()
                    .map(targetQuestion -> new AnswerSuggestDTO(targetQuestion.getQuestion().getQuestion(), target.getUsername()))
                    .filter(answerSuggest -> !finalFilledQuestions.contains(answerSuggest.getQuestion()))
                    .collect(Collectors.toList());

            return new ModelAndView("questionnaire", "answerSuggests", answerSuggestsDTO);
        }
        ProfileDTO profileDto = converter.profileToDto(user.getProfile());
        return new ModelAndView("profile", "profile", profileDto);
    }

    @GetMapping("friends")
    public ModelAndView friends(Principal principal) {
        List<User> friends = friendService.findAllUserFriends(userService.findByUsername(principal.getName()));
        List<FullInfoDTO> friendsDto = friends.stream().map(friend -> new FullInfoDTO(converter.userToDto(friend), converter.profileToDto(friend.getProfile()))).collect(Collectors.toList());
        return new ModelAndView("friends", "friends", friendsDto);
    }

    @GetMapping("allUsers")
    public ModelAndView allUsers(Principal principal) {
        List<User> users = userService.getAllUsers();
        users.remove(userService.findByUsername(principal.getName()));
        users.removeAll(friendService.findAllUserRelations(userService.findByUsername(principal.getName())));
        users = users.stream().filter(user -> user.getProfile().getUserQuestions().size() == questionSize).collect(Collectors.toList());
        List<FullInfoDTO> usersDto = users.stream().map(user -> new FullInfoDTO(converter.userToDto(user), converter.profileToDto(user.getProfile()))).collect(Collectors.toList());
        return new ModelAndView("all-users", "users", usersDto);
    }

    //todo метод для отладки, удалить позже
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
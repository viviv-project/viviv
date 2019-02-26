package ru.project.viviv.common;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.project.viviv.model.dto.*;
import ru.project.viviv.model.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {
    private ModelMapper modelMapper;

    public Converter() {
        modelMapper = new ModelMapper();
    }

    public UserDTO userToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public ProfileDTO profileToDto(Profile profile) {
        ProfileDTO profileDto = modelMapper.map(profile, ProfileDTO.class);
        profileDto.setAvatarImage(Base64.encodeBase64String(profile.getAvatarImage()));

        return profileDto;
    }

    public AnswerSuggestDTO suggestAnswerToDto (SuggestAnswer suggestAnswer, User user, User target){
        if (suggestAnswer == null) return null;
        AnswerSuggestDTO answerSuggestDto = new AnswerSuggestDTO();
        answerSuggestDto.setUsername(user.getUsername());
        answerSuggestDto.setAnswerSuggest(suggestAnswer.getAnswer().getAnswer());
        answerSuggestDto.setQuestion(suggestAnswer.getUserQuestion().getQuestion().getQuestion());
        answerSuggestDto.setQuestionAuthor(target.getUsername());
        return answerSuggestDto;
    }

    public QuestionDTO userQuestionToDto(UserQuestion userQuestion){
        QuestionDTO questionDto = new QuestionDTO();
        questionDto.setQuestion(userQuestion.getQuestion().getQuestion());
        questionDto.setAnswer(userQuestion.getAnswer().getAnswer());
        return questionDto;
    }

    public List<QuestionDTO> userQuestionsToDto(List<UserQuestion> userQuestions){
        return userQuestions.stream().map(this::userQuestionToDto).collect(Collectors.toList());

    }

    public ProfileQuestionDTO profileQuestionToDto(User user){
        ProfileDTO profileDto = profileToDto(user.getProfile());
        List<UserQuestion> userQuestions = user.getProfile().getUserQuestions();
        List<QuestionDTO> questionsDto = userQuestionsToDto(userQuestions);
        ProfileQuestionDTO profileQuestionDto = new ProfileQuestionDTO();
        profileQuestionDto.setQuestionsDto(questionsDto);
        profileQuestionDto.setProfileDto(profileDto);
        return profileQuestionDto;
    }
}

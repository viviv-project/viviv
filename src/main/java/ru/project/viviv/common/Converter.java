package ru.project.viviv.common;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.project.viviv.model.dto.AnswerSuggestDTO;
import ru.project.viviv.model.dto.ProfileDTO;
import ru.project.viviv.model.dto.UserDTO;
import ru.project.viviv.model.entity.Profile;
import ru.project.viviv.model.entity.SuggestAnswer;
import ru.project.viviv.model.entity.User;

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
}

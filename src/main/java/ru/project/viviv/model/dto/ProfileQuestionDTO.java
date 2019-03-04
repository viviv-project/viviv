package ru.project.viviv.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileQuestionDTO {
    private ProfileDTO profileDto;
    private List<QuestionDTO> questionsDto;
    private String email;
    private ProfileEditDTO profileEditDto;
}

package ru.project.viviv.model.dto;

import lombok.Data;

@Data
public class QuestionFillDTO {
    private String question;
    private String answer;
    private int filledCount;
    private String username;
}

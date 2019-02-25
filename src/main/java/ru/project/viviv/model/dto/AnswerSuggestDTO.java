package ru.project.viviv.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerSuggestDTO {
    private String questionAuthor;
    private String username;
    private String question;
    private String answerSuggest;

    public AnswerSuggestDTO(String question, String questionAuthor){
        this.question = question;
        this.questionAuthor = questionAuthor;
    }
}

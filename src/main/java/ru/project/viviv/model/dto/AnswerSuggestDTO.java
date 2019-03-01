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
    private Boolean status;

    private Boolean access;

    public AnswerSuggestDTO(String question, String questionAuthor, String username){
        this.question = question;
        this.questionAuthor = questionAuthor;
        this.username = username;
    }

    public AnswerSuggestDTO(Boolean access, String questionAuthor) {
        this.access = access;
        this.questionAuthor = questionAuthor;
    }
}

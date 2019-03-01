package ru.project.viviv.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_suggest_answer")
@Data
@NoArgsConstructor
public class SuggestAnswer {

    @Id
    @Column(name = "suggest_answer_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "request_user_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "user_question_id")
    private UserQuestion userQuestion;

    @Column
    private Boolean status;

    public SuggestAnswer(Answer answer, Profile profile, UserQuestion userQuestion, Boolean status) {
        this.answer = answer;
        this.profile = profile;
        this.userQuestion = userQuestion;
        this.status = status;
    }
}
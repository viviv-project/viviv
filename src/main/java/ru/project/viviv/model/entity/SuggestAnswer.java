package ru.project.viviv.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_suggest_answer")
@Data
public class SuggestAnswer {

    @Id
    @Column(name = "suggest_answer_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "request_user_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "user_question_id")
    private UserQuestion userQuestion;

}
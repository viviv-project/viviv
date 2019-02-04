package ru.project.viviv.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vi_question")
@Data
public class Question {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<UserQuestion> userQuestions = new ArrayList<>();
}
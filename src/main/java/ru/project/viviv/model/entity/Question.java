package ru.project.viviv.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_question")
@Data
public class Question {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //todo переименовать question
    @Column
    private String question;
}
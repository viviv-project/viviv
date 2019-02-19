package ru.project.viviv.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_answer")
@Data
@NoArgsConstructor
public class Answer {

    @Id
    @Column(name = "answer_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //todo переименовать answer
    @Column(unique = true)
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }
}
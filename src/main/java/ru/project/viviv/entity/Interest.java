package ru.project.viviv.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vi_interest")
@Data
public class Interest {
    @Id
    String interest_id;
    @Column
    String interest;
}

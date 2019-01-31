package ru.project.viviv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vi_interest")
@Data
public class Interest {
    @Id
    private String interest_id = UUID.randomUUID().toString();
    @Column
    private String interest;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "interests")
    private List<Profile> profiles = new ArrayList<>();
}
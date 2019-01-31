package ru.project.viviv.entity;

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
    @Column(name = "interest_id")
    String id = UUID.randomUUID().toString();

    @Column
    String interest;

//    @JsonIgnore
//    @ToString.Exclude
//    @ManyToMany(mappedBy = "interests")
//    List<Profile> profiles = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "interest", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<InterestConnection> interestConnections = new ArrayList<>();
}
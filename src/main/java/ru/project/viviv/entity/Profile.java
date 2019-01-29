package ru.project.viviv.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vi_profile")
@Data
public class Profile {

    @Id
    String user_id = UUID.randomUUID().toString();
    @Column
    String firstname;
    @Column
    String lastname;
    @Column
    String middlename;
    @Column
    String avatar_image;
    @Column
    LocalDateTime date_added;

    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "vi_user_interests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    List<Interest> interests = new ArrayList<>();
}
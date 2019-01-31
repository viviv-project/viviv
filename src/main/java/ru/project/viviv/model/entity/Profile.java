package ru.project.viviv.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "vi_profile")
@Data
public class Profile {

    @Id
    private String user_id = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String middlename;
    @Column
    private String avatar_image;
    @Column
    private LocalDateTime date_added;

    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "vi_user_interests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    private List<Interest> interests = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserQuestions> userQuestions = new ArrayList<>();
}
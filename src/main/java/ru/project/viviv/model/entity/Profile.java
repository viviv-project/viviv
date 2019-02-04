package ru.project.viviv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vi_profile")
@Data
public class Profile {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull(message = "не может быть пустым")
    @Column
    @Size(min = 1)
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String middlename;

    @Column(name = "avatar_image")
    private String avatarImage;

    @Column(name = "date_added")
    @CreationTimestamp
    private LocalDateTime dateAdded;

    @ToString.Exclude
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<InterestConnection> interestConnections = new ArrayList<>();

//    @ToString.Exclude
//    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Image> images = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<FriendSource> sourceFriends = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<FriendTarget> friendTargets = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<UserQuestion> userQuestions = new ArrayList<>();
}
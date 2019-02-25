package ru.project.viviv.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vi_profile")
@Data
public class Profile {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column
    @Size(min = 1)
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String middlename;

    @Lob
    @Column(name = "avatar_image")
    private byte[] avatarImage;

    @Column(name = "date_added")
    @CreationTimestamp
    private LocalDateTime dateAdded;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<InterestConnection> interestConnections = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserQuestion> userQuestions = new ArrayList<>();

}
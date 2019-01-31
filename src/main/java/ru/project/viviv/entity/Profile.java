package ru.project.viviv.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

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
    @Column(name = "user_id")
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id = UUID.randomUUID().toString();

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String middlename;
    @Column(name = "avatar_image")
    private String avatarImage;
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

//    @ToString.Exclude
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(
//            name = "vi_user_interests",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "interest_id"))
//    List<Interest> interests = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "profile", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<InterestConnection> interestConnections = new ArrayList<>();
}